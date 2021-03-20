package com.blue.project.modules.users.services;

import com.blue.project.config.PortalConfig;
import com.blue.project.modules.users.dao.SecurityRoleRepository;
import com.blue.project.modules.users.dao.UserRepository;
import com.blue.project.modules.users.dao.UserSecurityRoleRepository;
import com.blue.project.modules.users.dao.VerificationTokenRepository;
import com.blue.project.dto.StatusMessage;
import com.blue.project.modules.users.models.SecurityRole;
import com.blue.project.modules.users.models.User;
import com.blue.project.modules.users.models.UserSecurityRole;
import com.blue.project.modules.users.models.VerificationToken;
import com.blue.project.modules.users.dto.*;
import com.blue.project.service.EmailService;
import com.blue.project.service.NadiaService;
import name.mymiller.nadia.Nadia;
import name.mymiller.utils.ListUtils;
import name.mymiller.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.*;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.servlet.ServletContext;
import javax.transaction.Transactional;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    private static final int EXPIRATION = 60 * 24;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserSecurityRoleRepository userSecurityRoleRepository;

    @Autowired
    private SecurityRoleRepository securityRoleRepository;

    @Autowired
    private EmailService emailService;

    @Autowired
    private PortalConfig portalConfig;

    @Autowired
    private VerificationTokenRepository verificationTokenRepository;

    @Autowired
    private EntityManager entityManager;


    @Qualifier("userPasswordEncoder")
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private ServletContext context;

    @Autowired
    private NadiaService auditService;

    public StatusMessage registerUser(Registration registration) {
        if (this.userRepository.existsByUsername(registration.getUserName())) {
            return new StatusMessage("User Name not available", false);
        }
        VerificationToken verificationToken;
        if (this.verificationTokenRepository.existsByEmail(registration.getUserName())) {
            verificationToken = this.verificationTokenRepository.findFirstByEmail(registration.getUserName());
            verificationToken.setExpiration(this.calculateExpiryDate());
        } else {
            String token = UUID.randomUUID().toString();
            verificationToken = new VerificationToken();
            verificationToken.setVerification(token);
            verificationToken.setPassword(this.passwordEncoder.encode(registration.getPassword()));
            verificationToken.setEmail(registration.getUserName());
            verificationToken.setExpiration(this.calculateExpiryDate());
            this.verificationTokenRepository.save(verificationToken);
        }

        this.emailService.sendMessage(registration.getUserName(), "Link", portalConfig.getUri() + "/#/verification/" + verificationToken.getVerification());
        return new StatusMessage("Verification Email Sent", true);
    }

    @Override
    public StatusMessage userVerification(String token) {
        VerificationToken verificationToken = this.verificationTokenRepository.findFirstByVerification(token);
        if (verificationToken == null) {
            return new StatusMessage("Verification failed.", false);
        }

        User user = new User();

        user.setEnabled(false);
        user.setId(null);
        user.setUsername(verificationToken.getEmail());
        user.setPassword(verificationToken.getPassword());
        user.setEnabled(true);
        final User savedUser = this.userRepository.save(user);

        List<SecurityRole> defaultRoles = ListUtils.safe(this.securityRoleRepository.findAllByDefaultRoleTrue());

        defaultRoles.forEach(defaultRole -> {
            UserSecurityRole role = new UserSecurityRole();
            role.setUser(savedUser);
            role.setSecurityRole(defaultRole);

            this.userSecurityRoleRepository.save(role);
        });

        this.verificationTokenRepository.delete(verificationToken);

        return new StatusMessage("Email Verified", true);
    }

    private ZonedDateTime calculateExpiryDate() {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.MINUTE, UserServiceImpl.EXPIRATION);
        return cal.toInstant().atZone(ZoneId.systemDefault());
    }

    @Override
    public User getUserByUsername(String username) {
        return this.userRepository.findFirstByUsername(username);
    }

    @Override
    public List<SecurityRole> getRolesByUser(User user) {
        return userSecurityRoleRepository
                .findAllByUserId(user.getId())
                .stream()
                .map(UserSecurityRole::getSecurityRole)
                .distinct()
                .collect(Collectors.toList());
    }

    @Override
    public boolean hasAuthority(Collection<SecurityRole> roles, String role) {
        if (roles != null) {
            return roles.stream().anyMatch(securityRole -> securityRole.getAuthority().equals(role));
        }
        return false;
    }

    @Override
    public boolean hasAuthority(User user, String role) {
        return ListUtils.safe(this.userSecurityRoleRepository.findAllByUserId(user.getId())).stream().anyMatch(securityRole -> securityRole.getSecurityRole().getAuthority().equals(role));
    }

    @Override
    public boolean hasAuthority(User user, List<String> roles) {
        return ListUtils.safe(this.userSecurityRoleRepository.findAllByUserId(user.getId())).stream().anyMatch(securityRole -> roles.contains(securityRole.getSecurityRole().getAuthority()));
    }

    @Override
    public StatusMessage changeUserPassword(ChangeUserPassword changeUserPassword) {
        Optional<User> user = this.userRepository.findById(changeUserPassword.getUserId());

        if (user.isPresent()) {
            String newPassword = this.passwordEncoder.encode(changeUserPassword.getPassword());
            user.get().setPassword(newPassword);
            this.userRepository.save(user.get());
            return new StatusMessage("Password Changed", true);
        }

        return new StatusMessage("Unable to find user", false);
    }

    @Override
    public StatusMessage changePassword(ChangePassword changePassword) {
        User user = this.getUserByUsername(SecurityContextHolder.getContext().getAuthentication().getName());

        if (user != null) {
            if (!this.passwordEncoder.matches(changePassword.getCurrentPassword(), user.getPassword())) {
                return new StatusMessage("Current Password does not match.", false);
            }

            if (!changePassword.getNewPassword().equals(changePassword.getVerifyPassword())) {
                return new StatusMessage("New and Verify Password do not match", false);
            }

            String newPassword = this.passwordEncoder.encode(changePassword.getNewPassword());
            user.setPassword(newPassword);
            this.userRepository.save(user);

            return new StatusMessage("Password Changed", true);
        }

        return new StatusMessage("Unable to find user", false);
    }

    public UserSearch search(UserSearch userSearch) {
        Pageable pageable;
        Page<User> results;

        if (StringUtils.isNotNullOrEmpty(userSearch.getSort())) {
            if (userSearch.getAscending()) {
                pageable = PageRequest.of(userSearch.getPage(), userSearch.getSize(), Sort.by(userSearch.getSort()).ascending());
            } else {
                pageable = PageRequest.of(userSearch.getPage(), userSearch.getSize(), Sort.by(userSearch.getSort()).descending());
            }
        } else {
            pageable = PageRequest.of(userSearch.getPage(), userSearch.getSize());
        }

        if (StringUtils.isNotNullOrEmpty(userSearch.getUsername())) {
            ExampleMatcher customExampleMatcher = ExampleMatcher.matchingAny()
                    .withMatcher("username", ExampleMatcher.GenericPropertyMatchers.contains().ignoreCase());

            Example<User> example = Example.of(User.from(userSearch.getUsername()), customExampleMatcher);

            results = this.userRepository.findAll(example, pageable);
        } else {
            results = this.userRepository.findAll(pageable);
        }
        userSearch.setResults(results.toList());
        userSearch.setTotal(results.getTotalElements());

        return userSearch;
    }

    public User getCurrentUser() {
        return this.getUserByUsername(this.getCurrentUserName());
    }

    public String getCurrentUserName() {
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }

    public User userSave(User user) throws IllegalAccessException {
        Optional<User> existingUser = this.userRepository.findById(user.getId());
        if (existingUser.isPresent()) {
            Nadia.getInstance().updateEntity(existingUser.get(), user, this.getCurrentUserName(), "user", existingUser.get().getId().toString());
            return this.userRepository.save(user);
        }

        return null;
    }

    public User userCreate(User user) throws IllegalAccessException {
        user = this.userRepository.save(user);
        Nadia.getInstance().insertEntity(user, this.getCurrentUserName(), "user", user.getId().toString());
        return user;
    }

    public User userDelete(Long userId) throws IllegalAccessException {
        Optional<User> user = this.userRepository.findById(userId);
        if (user.isPresent()) {
            Nadia.getInstance().deleteEntity(user.get(), this.getCurrentUserName(), "user", user.get().getId().toString());

            List<UserSecurityRole> roles = ListUtils.safe(this.userSecurityRoleRepository.findAllByUserId(userId));
            for (UserSecurityRole role : roles) {
                Nadia.getInstance().deleteEntity(role, this.getCurrentUserName(), "user_security_role", role.getId().toString());
                this.userSecurityRoleRepository.delete(role);
            }


            this.userRepository.delete(user.get());
            return user.get();
        }

        return null;
    }

    public List<UserRole> getUserRoles(Long userId) {
        Map<Long, UserRole> map = ListUtils.safe(this.securityRoleRepository.findAll()).stream().map(role -> new UserRole(userId, role)).collect(Collectors.toMap(UserRole::getSecurityRoleId, Function.identity()));
        ListUtils.safe(this.userSecurityRoleRepository.findAllByUserId(userId)).forEach(role -> map.get(role.getSecurityRole().getId()).setActive(true));

        return ListUtils.mapValuesToList(map);
    }

    public List<UserRole> putUserRoles(long userId, List<UserRole> roles) {
        roles = ListUtils.safe(roles);
        if (roles.stream().anyMatch(role -> !role.getUserId().equals(userId))) {
            throw new IllegalArgumentException("User Id Mismatch");
        }
        Optional<User> user = this.userRepository.findById(userId);
        if (user.isEmpty()) {
            throw new IllegalArgumentException("User not found");
        }
        List<UserSecurityRole> deleteList = new ArrayList<>();
        List<UserSecurityRole> saveList = new ArrayList<>();

        roles.forEach(role -> {
            UserSecurityRole roleFound = this.userSecurityRoleRepository.findFirstByUser_IdAndSecurityRole_Id(userId, role.getSecurityRoleId());
            if (roleFound != null) {
                if (!role.getActive()) {
                    deleteList.add(roleFound);
                }
            } else if (role.getActive()) {
                UserSecurityRole newRole = new UserSecurityRole();
                newRole.setUser(user.get());
                //noinspection OptionalGetWithoutIsPresent
                newRole.setSecurityRole(this.securityRoleRepository.findById(role.getSecurityRoleId()).get());
                saveList.add(newRole);
            }
        });

        if (deleteList.size() > 0) {
            this.userSecurityRoleRepository.deleteAll(deleteList);
        }
        if (saveList.size() > 0) {
            this.userSecurityRoleRepository.saveAll(saveList);
        }

        this.entityManager.flush();
        return this.getUserRoles(userId);
    }
}

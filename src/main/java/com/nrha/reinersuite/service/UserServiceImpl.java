package com.nrha.reinersuite.service;

import com.nrha.reinersuite.config.PortalConfig;
import com.nrha.reinersuite.dao.SecurityRoleRepository;
import com.nrha.reinersuite.dao.UserRepository;
import com.nrha.reinersuite.dao.UserSecurityRoleRepository;
import com.nrha.reinersuite.dao.VerificationTokenRepository;
import com.nrha.reinersuite.dto.ChangePassword;
import com.nrha.reinersuite.dto.Registration;
import com.nrha.reinersuite.dto.StatusMessage;
import com.nrha.reinersuite.models.SecurityRole;
import com.nrha.reinersuite.models.User;
import com.nrha.reinersuite.models.UserSecurityRole;
import com.nrha.reinersuite.models.VerificationToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.servlet.ServletContext;
import javax.transaction.Transactional;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Calendar;
import java.util.List;
import java.util.UUID;
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


    public StatusMessage registerUser(Registration registration) throws Exception {
        if(this.userRepository.existsByUsername(registration.getUserName())) {
            return new StatusMessage("User Name not available",false);
        }
        VerificationToken verificationToken;
        if(this.verificationTokenRepository.existsByEmail(registration.getUserName())) {
            verificationToken = this.verificationTokenRepository.findFirstByEmail(registration.getUserName());
            verificationToken.setExpiration(this.calculateExpiryDate(UserServiceImpl.EXPIRATION));
        } else {
            String token = UUID.randomUUID().toString();
            verificationToken = new VerificationToken();
            verificationToken.setVerification(token);
            verificationToken.setPassword(this.passwordEncoder.encode(registration.getPassword()));
            verificationToken.setEmail(registration.getUserName());
            verificationToken.setExpiration(this.calculateExpiryDate(UserServiceImpl.EXPIRATION));
            this.verificationTokenRepository.save(verificationToken);
        }

        this.emailService.sendMessage(registration.getUserName(),"Link",portalConfig.getUri() + "/#/verification/" + verificationToken.getVerification());
        return new StatusMessage("Verification Email Sent",true);
    }

    @Override
    public StatusMessage userVerification(String token) {
        VerificationToken verificationToken = this.verificationTokenRepository.findFirstByVerification(token);
        if(verificationToken == null) {
            return new StatusMessage("Verification failed.",false);
        }

        User user = new User();
        UserSecurityRole role = new UserSecurityRole();

        user.setEnabled(false);
        user.setId(null);
        user.setUsername(verificationToken.getEmail());
        user.setPassword(verificationToken.getPassword());
        user.setEnabled(true);

        role.setUser(this.userRepository.save(user));
        SecurityRole authority = this.securityRoleRepository.findFirstByAuthority(SecurityRole.ROLE_MEMBER);
        role.setSecurityRole(authority);

        this.userSecurityRoleRepository.save(role);

        this.verificationTokenRepository.delete(verificationToken);

        return new StatusMessage("Email Verified",true);
    }

    private ZonedDateTime calculateExpiryDate(int expiryTimeInMinutes) {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.MINUTE, expiryTimeInMinutes);
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
    public boolean hasAuthority(List<SecurityRole> roles, String role) {
        if(roles != null) {
            return roles.stream().anyMatch(securityRole -> securityRole.getAuthority().equals(role));
        }
        return false;
    }

    @Override
    public StatusMessage changePassword(ChangePassword changePassword) {
        User user = this.getUserByUsername( SecurityContextHolder.getContext().getAuthentication().getName());

        if(user != null) {
            if(!this.passwordEncoder.matches(changePassword.getCurrentPassword(),user.getPassword()) ) {
                return new StatusMessage("Current Password does not match.", false);
            }

            if(!changePassword.getNewPassword().equals(changePassword.getVerifyPassword())) {
                return new StatusMessage("New and Verify Password do not match", false);
            }

            String newPassword = this.passwordEncoder.encode(changePassword.getNewPassword());
            user.setPassword(newPassword);
            this.userRepository.save(user);
        }

        return new StatusMessage("Password Changed",true);
    }
}

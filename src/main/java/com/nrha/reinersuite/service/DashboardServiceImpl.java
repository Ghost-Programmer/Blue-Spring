package com.nrha.reinersuite.service;

import com.nrha.reinersuite.dao.dashboard.DashboardRepository;
import com.nrha.reinersuite.dao.dashboard.DashboardTypeRepository;
import com.nrha.reinersuite.models.dashboard.Dashboard;
import com.nrha.reinersuite.models.dashboard.DashboardType;
import com.nrha.reinersuite.models.users.SecurityRole;
import com.nrha.reinersuite.models.users.User;
import name.mymiller.utils.ListUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class DashboardServiceImpl implements DashboardService{

    @Autowired
    private UserService userService;

    @Autowired
    private DashboardRepository dashboardRepository;

    @Autowired
    private DashboardTypeRepository dashboardTypeRepository;

    @Override
    public List<Dashboard> getUserDashboardComponents() {
        User user = this.userService.getCurrentUser();
        List<SecurityRole> roles = this.userService.getRolesByUser(user);

        List<Dashboard> dashboards = ListUtils.safe(this.dashboardRepository.findAllByUserIdOrderBySortAsc(user.getId()));

        return dashboards.stream().filter(item -> roles.contains(item.getType().getRole())).collect(Collectors.toList());
    }

    public List<DashboardType> getUserDashboardTypeAvailable() {
        User user = this.userService.getCurrentUser();
        List<SecurityRole> roles = this.userService.getRolesByUser(user);

        List<DashboardType> typeList = ListUtils.safe(this.dashboardTypeRepository.findAll());

        return typeList.stream().filter(item -> roles.contains(item.getRole())).collect(Collectors.toList());
    }
}

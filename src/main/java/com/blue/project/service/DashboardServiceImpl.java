package com.blue.project.service;

import com.blue.project.dao.dashboard.DashboardRepository;
import com.blue.project.dao.dashboard.DashboardTypeRepository;
import com.blue.project.models.dashboard.Dashboard;
import com.blue.project.models.dashboard.DashboardType;
import com.blue.project.models.users.SecurityRole;
import com.blue.project.models.users.User;
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

        if(ListUtils.isEmpty(dashboards)) {
            dashboards = this.getDefaultUserComponents(user);
            if(ListUtils.notEmpty(dashboards)) {
                for(int i = 0; i < dashboards.size();i++) {
                    dashboards.get(i).setSort(i+1);
                }
                dashboards = this.dashboardRepository.saveAll(dashboards);
            }
        }

        return dashboards.stream().filter(item -> roles.contains(item.getType().getRole())).collect(Collectors.toList());
    }

    public List<DashboardType> getUserDashboardTypeAvailable() {
        User user = this.userService.getCurrentUser();
        List<SecurityRole> roles = this.userService.getRolesByUser(user);

        List<DashboardType> typeList = ListUtils.safe(this.dashboardTypeRepository.findAll());

        return typeList.stream().filter(item -> roles.contains(item.getRole())).collect(Collectors.toList());
    }

    private List<Dashboard> getDefaultUserComponents(User user) {
        return ListUtils.safe(this.dashboardTypeRepository.findAllByDefaultItemIsTrue()).stream().map(type -> {
            Dashboard dashboard = new Dashboard();
            dashboard.setType(type);
            dashboard.setUserId(user.getId());
            dashboard.setColspan(type.getColspan());
            dashboard.setRowspan(type.getRowspan());
            dashboard.setData(type.getData());
            return dashboard;
        }).collect(Collectors.toList());
    }
}

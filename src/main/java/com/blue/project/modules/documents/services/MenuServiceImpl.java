package com.blue.project.modules.documents.services;


import com.blue.project.dto.StatusMessage;
import com.blue.project.modules.documents.dao.MenuItemAccessRepository;
import com.blue.project.modules.documents.dao.MenuItemRepository;
import com.blue.project.modules.documents.dao.MenuRepository;
import com.blue.project.modules.documents.dto.MenuDto;
import com.blue.project.modules.documents.models.Menu;
import com.blue.project.modules.documents.models.MenuItem;
import com.blue.project.modules.documents.models.MenuItemAccess;
import com.blue.project.modules.users.models.SecurityRole;
import com.blue.project.modules.users.services.UserService;
import name.mymiller.utils.ListUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class MenuServiceImpl implements MenuService {

    @Autowired
    private MenuRepository menuRepository;

    @Autowired
    private MenuItemRepository menuItemRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private MenuItemAccessRepository menuItemAccessRepository;
    @Override
    public Menu createMenu(Menu menu) {
        return this.menuRepository.save(menu);
    }

    @Override
    public Menu updateMenu(Menu menu) {
        return this.menuRepository.save(menu);
    }

    @Override
    public StatusMessage deleteMenu(Long id) {
        this.menuRepository.deleteById(id);
        return new StatusMessage().setOk(true);
    }

    @Override
    public MenuItem addItemToMenu(Long menuId, MenuItem item) {
        Menu menu = this.menuRepository.getById(menuId);
        item.setMenu(menu);
        item.setSubMenu(null);

        return this.menuItemRepository.save(item);
    }

    @Override
    public MenuItem removeItemFromMenu(MenuItem item) {
        item.setMenu(null);
        return this.menuItemRepository.save(item);
    }

    @Override
    public MenuItem setSubMenu(MenuItem item, Long menuId) {
        Menu menu = this.menuRepository.getById(menuId);
        item.setSubMenu(menu);

        return this.menuItemRepository.save(item);
    }

    @Override
    public MenuItem removeSubMenu(MenuItem item) {
        item.setSubMenu(null);

        return this.menuItemRepository.save(item);
    }

    @Override
    public void deleteMenuItem(Long id) {
        this.menuItemRepository.deleteById(id);
    }

    @Override
    public MenuItem creatMenuItem(MenuItem item) {
        return this.menuItemRepository.save(item);
    }

    @Override
    public MenuItem updateMenuItem(MenuItem item) {
        return this.menuItemRepository.save(item);
    }

    @Override
    public MenuDto getMenu(String menuName) {
        Menu menu = this.menuRepository.findFirstByName(menuName);
        MenuDto menuDto = null;

        if(menu != null) {
            menuDto = new MenuDto(menu);
            List<MenuItem> menuItems = new ArrayList<>();
            menu.getItems().forEach( item -> {
                List<SecurityRole> roles = ListUtils.safe(this.menuItemAccessRepository.findAllByMenuItemId((item.getId()))).stream().map(MenuItemAccess::getRole).collect(Collectors.toList());
                if(ListUtils.notEmpty(roles)) {
                    if(this.userService.currentUserHasAuthority(roles.stream().map(SecurityRole::getAuthority).collect(Collectors.toList()))) {
                       menuItems.add(item);
                    }
                } else  {
                    menuItems.add(item);
                }
            });
            menuDto.setItems(menuItems);
        }

        return menuDto;
    }

    @Override
    public MenuDto getMenuPublic(String menuName) {
        Menu menu = this.menuRepository.findFirstByName(menuName);
        MenuDto menuDto = null;
        if(menu != null) {
            menuDto = new MenuDto(menu);
            List<MenuItem> menuItems = new ArrayList<>();
            menu.getItems().forEach( item -> {
                List<SecurityRole> roles = ListUtils.safe(this.menuItemAccessRepository.findAllByMenuItemId((item.getId()))).stream().map(MenuItemAccess::getRole).collect(Collectors.toList());
                if(ListUtils.isEmpty(roles)) {
                    menuItems.add(item);
                }
            });
            menuDto.setItems(menuItems);
        }

        return menuDto;
    }
}

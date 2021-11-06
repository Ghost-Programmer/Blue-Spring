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
import java.util.Optional;
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
        Menu newMenu = new Menu();
        newMenu.setIcon(menu.getIcon());
        newMenu.setId(null);
        newMenu.setLock(false);
        newMenu.setName(menu.getName());
        return this.menuRepository.save(newMenu);
    }

    @Override
    public Menu updateMenu(MenuDto menu) {
        Optional<Menu> optional = this.menuRepository.findById(menu.getId());

        if(optional.isPresent()) {
            Menu update = optional.get();
            update.setName(menu.getName());
            update.setIcon(menu.getIcon());
            final Menu returnMenu = this.menuRepository.save(update);

            List<MenuItem> items = ListUtils.safe(this.menuItemRepository.findAllByMenu_IdOrderBySortAsc(menu.getId()));
            List<MenuItem> updateItems = ListUtils.safe(menu.getItems());
            updateItems.forEach(item -> {
                item.setMenu(returnMenu);
            });

            List<MenuItem> intersection = ListUtils.intersection(items, updateItems);

            List<MenuItem> itemsToRemove = ListUtils.unique(items, intersection);
            if(ListUtils.notEmpty(itemsToRemove)) {
                this.menuItemRepository.deleteAll(itemsToRemove);
            }

            List<MenuItem> itemsToAdd = ListUtils.unique(updateItems,intersection);
            if(ListUtils.notEmpty(itemsToAdd)) {
                this.menuItemRepository.saveAll(itemsToAdd);
            }

            List<MenuItem> itemsToUpdate = ListUtils.union(updateItems,intersection);
            if(ListUtils.notEmpty(itemsToUpdate)) {
                this.menuItemRepository.saveAll(itemsToUpdate);
            }

            return returnMenu;
        } else return null;
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
    public List<String> getMenuNames() {
        return ListUtils.safe(this.menuRepository.findAll()).stream().map(Menu::getName).sorted().collect(Collectors.toList());
    }

    @Override
    public MenuDto getMenu(Long menuId) {
        return this.getMenu(this.menuRepository.getById(menuId));
    }

    @Override
    public MenuDto getMenu(String menuName) {
        return this.getMenu(this.menuRepository.findFirstByName(menuName));
    }

    private MenuDto getMenu(Menu menu) {
        MenuDto menuDto = null;

        if(menu != null) {
            menuDto = new MenuDto(menu);
            List<MenuItem> menuItems = new ArrayList<>();
            ListUtils.safe(this.menuItemRepository.findAllByMenu_IdOrderBySortAsc(menu.getId())).forEach( item -> {
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
            ListUtils.safe(this.menuItemRepository.findAllByMenu_IdOrderBySortAsc(menu.getId())).forEach( item -> {
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

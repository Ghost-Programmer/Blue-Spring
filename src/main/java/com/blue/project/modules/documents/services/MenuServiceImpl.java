package com.blue.project.modules.documents.services;


import com.blue.project.dto.StatusMessage;
import com.blue.project.modules.documents.dao.MenuItemRepository;
import com.blue.project.modules.documents.dao.MenuRepository;
import com.blue.project.modules.documents.models.Menu;
import com.blue.project.modules.documents.models.MenuItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class MenuServiceImpl implements MenuService {

    @Autowired
    private MenuRepository menuRepository;

    @Autowired
    private MenuItemRepository menuItemRepository;

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
}

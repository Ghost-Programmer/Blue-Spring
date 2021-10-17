package com.blue.project.modules.documents.services;

import com.blue.project.dto.StatusMessage;
import com.blue.project.modules.documents.dto.MenuDto;
import com.blue.project.modules.documents.models.Menu;
import com.blue.project.modules.documents.models.MenuItem;

public interface MenuService {
    Menu createMenu(Menu menu);

    Menu updateMenu(Menu menu);

    StatusMessage deleteMenu(Long id);

    void deleteMenuItem(Long id);

    MenuItem creatMenuItem(MenuItem item);

    MenuItem updateMenuItem(MenuItem item);

    MenuItem addItemToMenu(Long menuId, MenuItem item);

    MenuItem removeItemFromMenu(MenuItem item);

    MenuItem setSubMenu(MenuItem item, Long menuId);

    MenuItem removeSubMenu(MenuItem item);

    MenuDto getMenu(String menuName);

    MenuDto getMenuPublic(String menuName);
}

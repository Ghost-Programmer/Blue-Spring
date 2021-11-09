package com.blue.project.modules.documents.controller;

import com.blue.project.dto.StatusMessage;
import com.blue.project.modules.documents.dto.MenuDto;
import com.blue.project.modules.documents.models.Menu;
import com.blue.project.modules.documents.models.MenuItem;
import com.blue.project.modules.documents.services.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import java.util.List;

@RestController
@RequestMapping(value = "/menu", produces = MediaType.APPLICATION_JSON_VALUE)
public class MenuController {
    @Autowired
    private MenuService menuService;

    @PostMapping("")
    @RolesAllowed("ROLE_MENU_EDITOR")
    public Menu createMenu(@RequestBody Menu menu) {
        return menuService.createMenu(menu);
    }

    @PutMapping("")
    @RolesAllowed("ROLE_MENU_EDITOR")
    public Menu updateMenu(@RequestBody MenuDto menu) {
        return menuService.updateMenu(menu);
    }

    @DeleteMapping("{id}")
    @RolesAllowed("ROLE_MENU_EDITOR")
    public StatusMessage updateMenu(@PathVariable("id") Long menuId) {
        return menuService.deleteMenu(menuId);
    }

    @GetMapping("")
    @RolesAllowed("ROLE_MENU_EDITOR")
    public List<String> getAllMenuNames() {
        return this.menuService.getMenuNames();
    }

    @PostMapping("/item")
    @RolesAllowed("ROLE_MENU_EDITOR")
    public MenuItem createMenuItem(@RequestBody MenuItem menuItem) {
        return menuService.creatMenuItem(menuItem);
    }

    @PutMapping("/item")
    @RolesAllowed("ROLE_MENU_EDITOR")
    public MenuItem updateMenuItem(@RequestBody MenuItem menuItem) {
        return menuService.updateMenuItem(menuItem);
    }

    @DeleteMapping("/item/{id}")
    @RolesAllowed("ROLE_MENU_EDITOR")
    public void deleteMenuItem(@PathVariable("id") Long mmenuItemId) {
        menuService.deleteMenuItem(mmenuItemId);
    }

    @GetMapping("/display/{name}")
    @PreAuthorize("permitAll()")
    public MenuDto getDisplayMenu(@PathVariable ("name") String menuName) {
        return menuService.getMenu(menuName);
    }
}

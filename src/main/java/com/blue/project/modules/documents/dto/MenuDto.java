package com.blue.project.modules.documents.dto;

import com.blue.project.modules.documents.models.Menu;
import com.blue.project.modules.documents.models.MenuItem;

import java.util.List;

public class MenuDto {
    private Long id;
    private String name;
    private String icon;
    private Boolean lock;
    private List<MenuItem> items;

    public MenuDto() {
    }

    public MenuDto(Menu menu) {
        this.id = menu.getId();
        this.name = menu.getName();
        this.icon = menu.getIcon();
        this.lock = menu.getLock();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public Boolean getLock() {
        return lock;
    }

    public void setLock(Boolean lock) {
        this.lock = lock;
    }

    public List<MenuItem> getItems() {
        return items;
    }

    public void setItems(List<MenuItem> items) {
        this.items = items;
    }
}

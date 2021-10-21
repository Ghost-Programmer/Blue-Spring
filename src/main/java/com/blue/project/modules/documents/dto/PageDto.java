package com.blue.project.modules.documents.dto;

import com.blue.project.modules.documents.models.Page;
import com.blue.project.modules.users.models.SecurityRole;

import javax.persistence.*;
import java.util.List;

public class PageDto {
    private Long id;
    private String uuid;
    private String name;
    private String icon;

    public PageDto(Page page) {
        this.id = page.getId();
        this.uuid = page.getUuid();
        this.name = page.getName();
        this.icon = page.getIcon();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
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

}

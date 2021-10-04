package com.blue.project.modules.meta.dto;

import java.util.List;

public class TableDto {
    private String name;
    private List<FieldDto> children;

    public TableDto() {
    }

    public TableDto(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<FieldDto> getChildren() {
        return children;
    }

    public void setChildren(List<FieldDto> children) {
        this.children = children;
    }
}

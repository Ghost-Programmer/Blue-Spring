package com.blue.project.modules.meta.dto;

import java.util.List;

public class SchemaDto {
    private String name;
    private List<TableDto> children;

    public SchemaDto() {
    }

    public SchemaDto(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<TableDto> getChildren() {
        return children;
    }

    public void setChildren(List<TableDto> children) {
        this.children = children;
    }
}

package com.blue.project.modules.meta.dto;

import java.util.List;

public class TableDto {
    private String table;
    private List<FieldDto> children;

    public TableDto() {
    }

    public TableDto(String table) {
        this.table = table;
    }

    public String getTable() {
        return table;
    }

    public void setTable(String table) {
        this.table = table;
    }

    public List<FieldDto> getChildren() {
        return children;
    }

    public void setChildren(List<FieldDto> children) {
        this.children = children;
    }
}

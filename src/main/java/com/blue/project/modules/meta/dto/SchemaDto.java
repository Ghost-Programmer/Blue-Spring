package com.blue.project.modules.meta.dto;

import java.util.List;

public class SchemaDto {
    private String schema;
    private List<TableDto> children;

    public SchemaDto() {
    }

    public SchemaDto(String schema) {
        this.schema = schema;
    }

    public String getSchema() {
        return schema;
    }

    public void setSchema(String schema) {
        this.schema = schema;
    }

    public List<TableDto> getChildren() {
        return children;
    }

    public void setChildren(List<TableDto> children) {
        this.children = children;
    }
}

package com.blue.project.modules.meta.dto;

public class FieldDto {
    private String name;
    private String type;
    private Boolean nullable;

    public FieldDto() {
    }

    public FieldDto(String name, String type, Boolean nullable) {
        this.name = name;
        this.type = type;
        this.nullable = nullable;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Boolean getNullable() {
        return nullable;
    }

    public void setNullable(Boolean nullable) {
        this.nullable = nullable;
    }
}

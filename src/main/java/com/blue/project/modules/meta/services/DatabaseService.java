package com.blue.project.modules.meta.services;

import com.blue.project.modules.meta.dto.SchemaDto;

import java.util.List;

public interface DatabaseService {
    List<SchemaDto> getDatabaseInfo();
}

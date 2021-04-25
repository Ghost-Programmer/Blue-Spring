package com.blue.project.modules.meta.services;

import com.blue.project.modules.meta.dto.FieldDto;
import com.blue.project.modules.meta.dto.SchemaDto;
import com.blue.project.modules.meta.dto.TableDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class DatabaseServiceImpl implements DatabaseService {

    protected final Logger logger = LoggerFactory.getLogger(this.getClass());

    @PersistenceContext
    private EntityManager entityManager;

    public List<SchemaDto> getDatabaseInfo() {
        Query schemaQuery = this.entityManager.createNativeQuery(
                "select schema_name as database_name from information_schema.schemata order by schema_name");

        List<Object[]> schemaResults = schemaQuery.getResultList();

        List<SchemaDto> databaseInfo =  schemaResults.stream()
                .map(item -> item[0])
                .map(Object::toString)
                .filter(schema -> ((String) schema).equalsIgnoreCase("core"))
                .filter(schema -> ((String) schema).equalsIgnoreCase("information_schema"))
                .filter(schema -> ((String) schema).equalsIgnoreCase("mysql"))
                .filter(schema -> ((String) schema).equalsIgnoreCase("performance_schema"))
                .map(schema -> new SchemaDto(schema)).collect(Collectors.toList());

        databaseInfo.forEach(schema -> {
            Query tableQuery = this.entityManager.createNativeQuery(
                    "show tables from " + schema.getSchema());

            List<Object[]> tableResults = tableQuery.getResultList();

            schema.setChildren(tableResults.stream()
                    .map(item -> item[0])
                    .map(Object::toString)
                    .map(table -> new TableDto(table))
                    .collect(Collectors.toList()));

            schema.getChildren().forEach(table -> {
                Query fieldQuery = this.entityManager.createNativeQuery(
                        "show columns from " + schema+"."+table
                );

                List<Object[]> fieldResults = fieldQuery.getResultList();

                table.setChildren(fieldResults.stream().map(field -> new FieldDto(field[0].toString(),field[1].toString(),Boolean.parseBoolean(field[2].toString())))
                        .collect(Collectors.toList()));
            });
        });


        return databaseInfo;
    }
}

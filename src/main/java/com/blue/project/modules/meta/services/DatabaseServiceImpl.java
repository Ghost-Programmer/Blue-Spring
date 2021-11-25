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
import java.util.ArrayList;
import java.util.List;

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

        List<SchemaDto> databaseInfo = new ArrayList<>();

        for (Object schema : schemaResults) {
            String name = schema.toString();

            if (!name.equalsIgnoreCase("core")
                    && !name.equalsIgnoreCase("information_schema")
                    && !name.equalsIgnoreCase("mysql")
                    && !name.equalsIgnoreCase("performance_schema")) {
                databaseInfo.add(new SchemaDto(name));
            }
        }

        databaseInfo.forEach(schema -> {
            Query tableQuery = this.entityManager.createNativeQuery(
                    "show tables from " + schema.getName());

            List<Object[]> tableResults = tableQuery.getResultList();

            schema.setChildren(new ArrayList<>());

            for (Object table : tableResults) {
                String tableName = table.toString();
                TableDto tableDto = new TableDto(tableName);
                schema.getChildren().add(tableDto);

                Query fieldQuery = this.entityManager.createNativeQuery(
                        "show columns from " + schema.getName() + "." + tableName
                );

                List<Object[]> fieldResults = fieldQuery.getResultList();


                tableDto.setChildren(new ArrayList<>());
                for (Object[] fields : fieldResults) {
                    FieldDto fieldDto = new FieldDto(fields[0].toString(), fields[1].toString(), Boolean.parseBoolean(fields[2].toString()));
                    tableDto.getChildren().add(fieldDto);
                }
            }
        });


        return databaseInfo;
    }
}

package com.blue.project.modules.meta.controller;

import com.blue.project.modules.meta.dao.CountryRepository;
import com.blue.project.modules.meta.dto.SchemaDto;
import com.blue.project.modules.meta.models.Country;
import com.blue.project.modules.meta.services.DatabaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Sort;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.security.PermitAll;
import java.util.List;

@RestController
@RequestMapping(value = "/meta", produces = MediaType.APPLICATION_JSON_VALUE)
public class MetaController {

    @Autowired
    private CountryRepository countryRepository;

    @Autowired
    private DatabaseService databaseService;

    @RequestMapping(value = "/countries", method = RequestMethod.GET)
    @Cacheable("countries")
    @PermitAll
    public List<Country> getCountries() {
        return this.countryRepository.findAll(Sort.by(Sort.Direction.ASC, "sort"));
    }

    @RequestMapping(value = "/database", method = RequestMethod.GET)
    @Cacheable("database_dto")
    @PermitAll
    public List<SchemaDto> getDatabaseInfo() {
        return this.databaseService.getDatabaseInfo();
    }
}

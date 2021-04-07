package com.blue.project.modules.meta.controller;

import com.blue.project.modules.meta.dao.CountryRepository;
import com.blue.project.modules.meta.models.Country;
import name.mymiller.utils.ListUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.security.PermitAll;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/meta", produces = MediaType.APPLICATION_JSON_VALUE)
public class MetaController {

    @Autowired
    private CountryRepository countryRepository;

    @RequestMapping(value = "/countries", method = RequestMethod.GET)
    @PermitAll
    public List<Country> downloadDocument(@PathVariable("id") Long documentId) {
        return this.countryRepository.findAll(Sort.by(Sort.Direction.ASC,"sort"));
    }
}

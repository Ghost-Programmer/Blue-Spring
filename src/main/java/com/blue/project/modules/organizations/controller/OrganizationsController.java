package com.blue.project.modules.organizations.controller;

import com.blue.project.modules.organizations.dao.OrganizationsRepository;
import com.blue.project.modules.organizations.models.Organizations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.security.PermitAll;
import java.util.List;

@RestController
@RequestMapping(value = "/ogranizations", produces = MediaType.APPLICATION_JSON_VALUE)
public class OrganizationsController {

    @Autowired
    private OrganizationsRepository organizationsRepository;

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    @PermitAll
    public List<Organizations> downloadDocument(@PathVariable("id") Long documentId) {
        return this.organizationsRepository.findAll(Sort.by(Sort.Direction.ASC,"name"));
    }
}

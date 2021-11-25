package com.blue.project.modules.documents.controller;

import com.blue.project.dto.StatusMessage;
import com.blue.project.modules.documents.dto.PageDto;
import com.blue.project.modules.documents.dto.PageSearch;
import com.blue.project.modules.documents.models.Page;
import com.blue.project.modules.documents.services.PageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import java.util.List;

@RestController
@RequestMapping(value = "/page", produces = MediaType.APPLICATION_JSON_VALUE)
public class PageController {

    @Autowired
    private PageService pageService;

    @PostMapping("{uuid}")
    @PreAuthorize("hasRole(T(com.blue.project.modules.users.models.SecurityRole).ROLE_PAGE_EDITOR)")
    public Page docSearch(@PathVariable("uuid") String uuid) {
        return pageService.findByUuid(uuid);
    }

    @PostMapping("")
    @PreAuthorize("hasRole(T(com.blue.project.modules.users.models.SecurityRole).ROLE_PAGE_EDITOR)")
    public Page createPage(@RequestBody Page page) {
        return pageService.createPage(page);
    }

    @PutMapping("")
    @PreAuthorize("hasRole(T(com.blue.project.modules.users.models.SecurityRole).ROLE_PAGE_EDITOR)")
    public Page updatePage(@RequestBody Page page) {
        return pageService.updatePage(page);
    }

    @DeleteMapping("{id}")
    @PreAuthorize("hasRole(T(com.blue.project.modules.users.models.SecurityRole).ROLE_PAGE_EDITOR)")
    public StatusMessage updatePage(@PathVariable("id") Long id) {
        return pageService.deletePage(id);
    }

    @PostMapping("search")
    public PageSearch docSearch(@RequestBody PageSearch pageSearch) {
        return pageService.search(pageSearch);
    }

    @GetMapping("/{uuid}")
    @RolesAllowed("ROLE_USER")
    public Page findByUuid(@PathVariable("uuid") String uuid) {
        return pageService.findByUuid(uuid);
    }

    @GetMapping("")
    @RolesAllowed("ROLE_MENU_EDITOR")
    public List<PageDto> getAllPages() {
        return this.pageService.getAllPages();
    }
}

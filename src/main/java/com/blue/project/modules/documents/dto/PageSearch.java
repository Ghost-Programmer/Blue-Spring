package com.blue.project.modules.documents.dto;

import com.blue.project.modules.documents.models.Document;
import com.blue.project.modules.documents.models.Page;

import java.time.ZonedDateTime;
import java.util.List;

public class PageSearch {
    private String name;
    private String uuid;

    private Integer page;
    private Integer size;
    private List<Page> results;
    private Long total;
    private String sort;
    private Boolean ascending;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public List<Page> getResults() {
        return results;
    }

    public void setResults(List<Page> results) {
        this.results = results;
    }

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    public Boolean getAscending() {
        return ascending;
    }

    public void setAscending(Boolean ascending) {
        this.ascending = ascending;
    }
}

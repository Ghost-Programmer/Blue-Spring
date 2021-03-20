package com.blue.project.modules.maintenance.dto;

import com.blue.project.modules.maintenance.models.Scheduled;

import java.util.List;

public class MaintenanceSearch {

    private Integer page;
    private Integer size;
    private List<Scheduled> results;
    private Long total;
    private String sort;
    private Boolean ascending;

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

    public List<Scheduled> getResults() {
        return results;
    }

    public void setResults(List<Scheduled> results) {
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

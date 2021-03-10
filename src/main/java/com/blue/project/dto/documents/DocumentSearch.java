package com.blue.project.dto.documents;

import com.blue.project.models.documents.Document;

import java.time.ZonedDateTime;
import java.util.List;

public class DocumentSearch {
    private String username;
    private String contentType;
    private ZonedDateTime date;
    private String fileName;
    private Long sizeFilter;

    private List<String> contentTypes;

    private Integer page;
    private Integer size;
    private List<Document> results;
    private Long total;
    private String sort;
    private Boolean ascending;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
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

    public List<Document> getResults() {
        return results;
    }

    public void setResults(List<Document> results) {
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

    public ZonedDateTime getDate() {
        return date;
    }

    public void setDate(ZonedDateTime date) {
        this.date = date;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public List<String> getContentTypes() {
        return contentTypes;
    }

    public void setContentTypes(List<String> contentTypes) {
        this.contentTypes = contentTypes;
    }

    public Long getSizeFilter() {
        return sizeFilter;
    }

    public void setSizeFilter(Long sizeFilter) {
        this.sizeFilter = sizeFilter;
    }
}

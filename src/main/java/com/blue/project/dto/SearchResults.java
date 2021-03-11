package com.blue.project.dto;

import java.util.List;

public class SearchResults<T> {
    private List<T> results;
    private Long totalCount;

    public List<T> getResults() {
        return results;
    }

    public void setResults(List<T> results) {
        this.results = results;
    }

    public Long getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Long totalCount) {
        this.totalCount = totalCount;
    }
}

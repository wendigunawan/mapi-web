package com.mapi.ihrd.queryfilter;

import org.springframework.data.domain.PageRequest;

public class BaseQueryFilter {

    private PageRequest pageRequest;
    private String searchText;

    public PageRequest getPageRequest() {
        return pageRequest;
    }

    public void setPageRequest(PageRequest pageRequest) {
        this.pageRequest = pageRequest;
    }

    public String getSearchText() {
        return searchText;
    }

    public void setSearchText(String searchText) {
        this.searchText = searchText;
    }
}

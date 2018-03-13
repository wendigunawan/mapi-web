package com.mapi.ihrd.response;

import java.util.Collection;

public class DataList<T> {

    private int totalCount = 0;
    private int filteredCount = 0;
    private Collection<T> list;

    public DataList(Collection<T> list) {
        this.list = list;
        filteredCount = list == null ? 0 : list.size();
        totalCount = filteredCount;
    }

    public DataList(int totalCount, Collection<T> list) {
        this.totalCount = totalCount;
        this.list = list;
        filteredCount = list == null ? 0 : list.size();
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public int getFilteredCount() {
        return filteredCount;
    }

    public void setFilteredCount(int filteredCount) {
        this.filteredCount = filteredCount;
    }

    public Collection<T> getList() {
        return list;
    }

    public void setList(Collection<T> list) {
        this.list = list;
    }
}

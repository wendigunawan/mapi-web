package com.mapi.ihrd.response;

import java.util.Collection;
import java.util.Collections;

public class JQueryDataTable<T> {

    private int draw = 1;
    private long recordsTotal = 0;
    private long recordsFiltered = 0;
    private Collection<T> data = Collections.emptyList();

    public JQueryDataTable(Collection<T> data) {
        this.data = data;
        this.recordsFiltered = data == null ? 0 : data.size();
        this.recordsTotal = data == null ? 0 : data.size();
    }


    public JQueryDataTable(Collection<T> data, long recordsTotal, int draw) {
        this.recordsTotal = recordsTotal;
        this.recordsFiltered = recordsTotal;
        this.data = data;
        this.draw = draw;
    }

    public int getDraw() {
        return draw;
    }

    public void setDraw(int draw) {
        this.draw = draw;
    }

    public long getRecordsTotal() {
        return recordsTotal;
    }

    public void setRecordsTotal(long recordsTotal) {
        this.recordsTotal = recordsTotal;
    }

    public long getRecordsFiltered() {
        return recordsFiltered;
    }

    public void setRecordsFiltered(long recordsFiltered) {
        this.recordsFiltered = recordsFiltered;
    }

    public Collection<T> getData() {
        return data;
    }

    public void setData(Collection<T> data) {
        this.data = data;
    }


}

package com.mapi.ihrd.response;

import com.mapi.ihrd.config.Constant;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JQueryDataTableInput {

    private Integer draw = 1;
    private Integer start = 0;
    private Integer length = Constant.DISPLAY_LENGTH;

    /*private Search search = new Search();
    private List<Order> order = new ArrayList<Order>();
    private List<Column> columns = new ArrayList<Column>();*/

    private Map<String, Object> search = new HashMap<>();
    private List<Map<String, Object>> order = new ArrayList<>();

    public Integer getDraw() {
        return draw;
    }

    public void setDraw(Integer draw) {
        this.draw = draw;
    }

    public Integer getStart() {
        return start;
    }

    public void setStart(Integer start) {
        this.start = start;
    }

    public Integer getLength() {
        return length;
    }

    public void setLength(Integer length) {
        this.length = length;
    }

    public Map<String, Object> getSearch() {
        return search;
    }

    public void setSearch(Map<String, Object> search) {
        this.search = search;
    }

    public List<Map<String, Object>> getOrder() {
        return order;
    }

    public void setOrder(List<Map<String, Object>> order) {
        this.order = order;
    }

/*public Search getSearch() {
        return search;
    }

    public void setSearch(Search search) {
        this.search = search;
    }

    public List<Order> getOrder() {
        return order;
    }

    public void setOrder(List<Order> order) {
        this.order = order;
    }

    public List<Column> getColumns() {
        return columns;
    }

    public void setColumns(List<Column> columns) {
        this.columns = columns;
    }*/

    public String getSearchText() {
        if (!search.isEmpty()) {
            return search.get("value").toString();
        }
        return null;
    }

    @Override
    public String toString() {
        return "JQueryDataTableInput{" +
                "draw=" + draw +
                ", start=" + start +
                ", length=" + length +
                ", search=" + search +
                ", order=" + order +
                '}';
    }
}

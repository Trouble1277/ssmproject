package com.entity;

public class Limits {

    private Integer start;

    private Integer limit;

    public Limits() {
    }

    public Limits(Integer start, Integer limit) {
        this.start = start;
        this.limit = limit;
    }

    public Integer getStart() {
        return start;
    }

    public void setStart(Integer start) {
        this.start = start;
    }

    public Integer getLimit() {
        return limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }
}

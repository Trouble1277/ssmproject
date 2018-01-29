package com.entity;

import java.util.List;

public class DataDictionaryEntity {
    private int ddid;
    private String ddName;
    private List<DataDictionarySonEntity> list;


    public List<DataDictionarySonEntity> getList() {
        return list;
    }

    public void setList(List<DataDictionarySonEntity> list) {
        this.list = list;
    }

    public int getDdid() {
        return ddid;
    }

    public void setDdid(int ddid) {
        this.ddid = ddid;
    }

    public String getDdName() {
        return ddName;
    }

    public void setDdName(String ddName) {
        this.ddName = ddName;
    }
}

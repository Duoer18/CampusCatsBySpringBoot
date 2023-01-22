package com.duoer.campus.entity;

import lombok.Data;

@Data
public abstract class MyRecord {
    protected Integer recordId;
    protected Integer catId;
    protected String catName;
    protected String username;
    protected Integer locationId;
    protected String location;
    protected String recordTime;
    protected String lastUpdate;
    protected Integer formerId;
}

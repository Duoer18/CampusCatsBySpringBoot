package com.duoer.campus.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class FeedingRecord extends MyRecord {
    private String remarks;
}

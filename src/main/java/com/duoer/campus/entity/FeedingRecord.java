package com.duoer.campus.entity;

import lombok.*;

@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@NoArgsConstructor
@Data
public class FeedingRecord extends MyRecord {
    private String remarks;

    public FeedingRecord(Integer recordId, Integer catId, String username,
                         Integer locationId, String recordTime, String lastUpdate, String remarks) {
        super(recordId, catId, username, locationId, recordTime, lastUpdate);
        this.remarks = remarks;
    }
}

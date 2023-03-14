package com.duoer.campus.entity;

import lombok.*;

@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@NoArgsConstructor
@Data
public class FeedingRecord extends MyRecord {
    private String remarks;

    public FeedingRecord(Long recordId, Long catId, String username,
                         Long locationId, String recordTime, String lastUpdate, String remarks) {
        this.remarks = remarks;
    }
}

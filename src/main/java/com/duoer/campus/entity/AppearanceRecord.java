package com.duoer.campus.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@NoArgsConstructor
@Data
public class AppearanceRecord extends MyRecord {
    public AppearanceRecord(Long recordId, Long catId, String username,
                            Long locationId, String recordTime, String lastUpdate) {
    }
}

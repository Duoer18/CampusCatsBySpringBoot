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
    public AppearanceRecord(Integer recordId, Integer catId, String username,
                            Integer locationId, String recordTime, String lastUpdate) {
        super(recordId, catId, username, locationId, recordTime, lastUpdate);
    }
}

package com.duoer.campus.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Data
public class AppearanceRecordDTO extends RecordDTO {
    private Integer formerId;

    public AppearanceRecordDTO(Integer recordId, Integer catId, String catName,
                               String username, Integer locationId, String location,
                               String recordTime, String lastUpdate, Integer formerId) {
        super(recordId, catId, catName, username, locationId, location, recordTime, lastUpdate);
        this.formerId = formerId;
    }
}
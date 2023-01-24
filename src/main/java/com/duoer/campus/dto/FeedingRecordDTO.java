package com.duoer.campus.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Data
public class FeedingRecordDTO extends RecordDTO {
    private String remarks;
    private Integer formerId;

    public FeedingRecordDTO(Integer recordId, Integer catId, String catName,
                            String username, Integer locationId, String location,
                            String recordTime, String lastUpdate, String remarks, Integer formerId) {
        super(recordId, catId, catName, username, locationId, location, recordTime, lastUpdate);
        this.remarks = remarks;
        this.formerId = formerId;
    }
}

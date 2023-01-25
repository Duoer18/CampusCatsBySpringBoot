package com.duoer.campus.dto;

import lombok.*;

@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Data
@NoArgsConstructor
public class FeedingRecordDTO extends RecordDTO {
    private String remarks;
    private Long formerId;

    public FeedingRecordDTO(Long recordId, Long catId, String catName,
                            String username, Long locationId, String location,
                            String recordTime, String lastUpdate, String remarks, Long formerId) {
        super(recordId, catId, catName, username, locationId, location, recordTime, lastUpdate);
        this.remarks = remarks;
        this.formerId = formerId;
    }
}

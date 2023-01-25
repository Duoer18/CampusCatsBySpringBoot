package com.duoer.campus.dto;

import lombok.*;

@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Data
@NoArgsConstructor
public class AppearanceRecordDTO extends RecordDTO {
    private Long formerId;

    public AppearanceRecordDTO(Long recordId, Long catId, String catName,
                               String username, Long locationId, String location,
                               String recordTime, String lastUpdate, Long formerId) {
        super(recordId, catId, catName, username, locationId, location, recordTime, lastUpdate);
        this.formerId = formerId;
    }
}

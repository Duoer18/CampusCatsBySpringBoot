package com.duoer.campus.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public abstract class RecordDTO {
    private Long recordId;
    private Long catId;
    private String catName;
    private String username;
    private Long locationId;
    private String location;
    private String recordTime;
    private String lastUpdate;
}

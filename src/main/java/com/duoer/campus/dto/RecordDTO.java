package com.duoer.campus.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public abstract class RecordDTO {
    private Integer recordId;
    private Integer catId;
    private String catName;
    private String username;
    private Integer locationId;
    private String location;
    private String recordTime;
    private String lastUpdate;
}

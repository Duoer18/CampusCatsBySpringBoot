package com.duoer.campus.dto;

import com.duoer.campus.entity.AppearanceRecord;
import lombok.*;

@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Data
@NoArgsConstructor
public class AppearanceRecordDTO extends AppearanceRecord {
    private String catName;
    private String location;
}

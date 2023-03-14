package com.duoer.campus.dto;

import com.duoer.campus.entity.FeedingRecord;
import lombok.*;

@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Data
@NoArgsConstructor
public class FeedingRecordDTO extends FeedingRecord {
    private String catName;
    private String location;
}

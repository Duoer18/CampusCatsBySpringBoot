package com.duoer.campus.entity;

import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.Version;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@NoArgsConstructor
@Data
@TableName("feeding_record_tmp")
public class FeedingRecordTemp extends FeedingRecord {
    private Integer formerId;
    @TableLogic(value = "0", delval = "1")
    private Integer deleted;
    @Version
    private Integer version;

    public FeedingRecordTemp(FeedingRecord fr, Integer formerId) {
        super(null, fr.getCatId(), fr.getUsername(),
                fr.getLocationId(), fr.getRecordTime(), fr.getLastUpdate(), fr.getRemarks());
        this.formerId = formerId;
    }
}

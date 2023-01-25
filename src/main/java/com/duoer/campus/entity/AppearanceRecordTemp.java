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
@TableName("appearance_record_tmp")
public class AppearanceRecordTemp extends AppearanceRecord {
    private Long formerId;
    @TableLogic(value = "0", delval = "1")
    private Integer deleted;
    @Version
    private Integer version;

    public AppearanceRecordTemp(AppearanceRecord ar, Long formerId) {
        super(null, ar.getCatId(), ar.getUsername(), ar.getLocationId(),
                ar.getRecordTime(), ar.getLastUpdate());
        this.formerId = formerId;
    }
}

package com.duoer.campus.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public abstract class MyRecord {
    @TableId(type = IdType.AUTO)
    private Integer recordId;
    private Integer catId;
    private String username;
    private Integer locationId;
    private String recordTime;
    private String lastUpdate;
}

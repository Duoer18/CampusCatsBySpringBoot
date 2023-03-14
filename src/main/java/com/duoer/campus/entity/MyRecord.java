package com.duoer.campus.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public abstract class MyRecord {
    @TableId
    private Long recordId;
    private Long catId;
    private String username;
    private Long locationId;
    private String recordTime;
    private String lastUpdate;
    private Integer deleted;
    private Integer needCheck;
}

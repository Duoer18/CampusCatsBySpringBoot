package com.duoer.campus.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

@Data
public class Location {
    @TableId(type = IdType.AUTO)
    private Integer locationId;
    private String location;
}

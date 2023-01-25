package com.duoer.campus.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

@Data
public class Location {
    @TableId
    private Long locationId;
    private String location;
}

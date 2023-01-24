package com.duoer.campus.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

@Data
public class Color {
    @TableId(type = IdType.AUTO)
    private Integer colorId;
    private String color;
}

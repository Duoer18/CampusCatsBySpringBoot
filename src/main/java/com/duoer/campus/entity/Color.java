package com.duoer.campus.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

@Data
public class Color {
    @TableId
    private Long colorId;
    private String color;
}

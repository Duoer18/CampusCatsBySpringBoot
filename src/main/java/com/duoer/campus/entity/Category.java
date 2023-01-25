package com.duoer.campus.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

@Data
public class Category {
    @TableId
    private Long categoryId;
    private String category;
}

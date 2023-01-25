package com.duoer.campus.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Cat {
    @TableId
    private Long catId;
    private String catName;
    private Long categoryId;
    private Long colorId;
    private Long characterId;
    private Long locationId;
    private Integer recordCount;
}

package com.duoer.campus.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Cat {
    @TableId(type = IdType.AUTO)
    private Integer catId;
    private String catName;
    private Integer categoryId;
    private Integer colorId;
    private Integer characterId;
    private Integer locationId;
    private Integer recordCount;
}

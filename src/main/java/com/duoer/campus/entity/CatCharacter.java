package com.duoer.campus.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

@Data
public class CatCharacter {
    @TableId(type = IdType.AUTO)
    private Integer characterId;
    private String catCharacter;
}

package com.duoer.campus.dto;

import com.duoer.campus.entity.Cat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CatDTO extends Cat {
    private String catName;
    private String category;
    private String color;
    private String character;
    private String location;
}

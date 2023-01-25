package com.duoer.campus.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CatDTO {
    private Long catId;
    private String catName;
    private Long categoryId;
    private String category;
    private Long colorId;
    private String color;
    private Long characterId;
    private String character;
    private Long locationId;
    private String location;
    private Integer recordCount;
    private String username;
}

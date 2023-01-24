package com.duoer.campus.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CatDTO {
    private Integer catId;
    private String catName;
    private Integer categoryId;
    private String category;
    private Integer colorId;
    private String color;
    private Integer characterId;
    private String character;
    private Integer locationId;
    private String location;
    private Integer recordCount;
    private String username;
}

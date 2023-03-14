package com.duoer.campus.dto.wrapper;

import com.duoer.campus.dao.CategoryMapper;
import com.duoer.campus.dao.CharacterMapper;
import com.duoer.campus.dao.ColorMapper;
import com.duoer.campus.dao.LocationMapper;
import com.duoer.campus.dto.CatDTO;
import com.duoer.campus.entity.Cat;
import com.duoer.campus.entity.CatTemp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CatDTOWrapper {
    @Autowired
    private CategoryMapper categoryMapper;
    @Autowired
    private ColorMapper colorMapper;
    @Autowired
    private CharacterMapper characterMapper;
    @Autowired
    private LocationMapper locationMapper;

    public CatDTO wrapCat(Cat c) {
        return new CatDTO();
    }
}

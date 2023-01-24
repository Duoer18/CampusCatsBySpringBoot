package com.duoer.campus.service.impl;

import com.duoer.campus.dao.CategoryMapper;
import com.duoer.campus.dao.CharacterMapper;
import com.duoer.campus.dao.ColorMapper;
import com.duoer.campus.dao.LocationMapper;
import com.duoer.campus.entity.CatCharacter;
import com.duoer.campus.entity.Category;
import com.duoer.campus.entity.Color;
import com.duoer.campus.entity.Location;
import com.duoer.campus.service.NormalAttributesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NormalAttributesServiceImpl implements NormalAttributesService {
    @Autowired
    private CategoryMapper categoryMapper;
    @Autowired
    private ColorMapper colorMapper;
    @Autowired
    private CharacterMapper characterMapper;
    @Autowired
    private LocationMapper locationMapper;

    /**
     * 获取全部品种
     *
     * @return 全部品种集合
     */
    @Override
    public List<Category> getAllCategories() {
        return categoryMapper.selectList(null);
    }

    /**
     * 获取全部颜色
     *
     * @return 全部颜色集合
     */
    @Override
    public List<Color> getAllColors() {
        return colorMapper.selectList(null);
    }

    /**
     * 获取全部性格
     *
     * @return 全部性格集合
     */
    @Override
    public List<CatCharacter> getAllCharacters() {
        return characterMapper.selectList(null);
    }

    /**
     * 获取全部位置
     *
     * @return 全部位置集合
     */
    @Override
    public List<Location> getAllLocations() {
        return locationMapper.selectList(null);
    }
}

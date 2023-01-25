package com.duoer.campus.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.duoer.campus.dao.CatMapper;
import com.duoer.campus.dao.CatTempMapper;
import com.duoer.campus.dto.CatDTO;
import com.duoer.campus.dto.wrapper.CatDTOWrapper;
import com.duoer.campus.entity.Cat;
import com.duoer.campus.entity.CatTemp;
import com.duoer.campus.service.CatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CatServiceImpl implements CatService {
    @Autowired
    private CatMapper catMapper;
    @Autowired
    private CatTempMapper catTempMapper;
    @Autowired
    private CatDTOWrapper catDTOWrapper;

    /**
     * 获取所有猫咪
     *
     * @param isTmp 是否临时表
     * @return 所有猫咪集合
     */
    @Override
    public List<CatDTO> getAllCats(boolean isTmp) {
        ArrayList<CatDTO> cats = new ArrayList<>();
        List<? extends Cat> catList = isTmp ?
                catTempMapper.selectList(null) :
                catMapper.selectList(null);
        for (Cat cat : catList) {
            cats.add(catDTOWrapper.wrapCat(cat));
        }
        return cats;
    }

    /**
     * 按id查猫咪
     *
     * @param id    编号
     * @param isTmp 是否临时表
     * @return 猫咪对象
     */
    @Override
    public CatDTO getCatById(long id, boolean isTmp) {
        Cat cat = isTmp ? catTempMapper.selectById(id) : catMapper.selectById(id);
        return catDTOWrapper.wrapCat(cat);
    }

    /**
     * 多条件查找
     *
     * @param c 猫咪对象
     * @return 所有猫咪集合
     */
    @Override
    public List<CatDTO> catRandomSearch(Cat c) {
        ArrayList<CatDTO> cats = new ArrayList<>();

        LambdaQueryWrapper<Cat> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(null != c.getCatName(), Cat::getCatName, c.getCatName())
                .eq(null != c.getCategoryId(), Cat::getCategoryId, c.getCategoryId())
                .eq(null != c.getColorId(), Cat::getColorId, c.getColorId())
                .eq(null != c.getCharacterId(), Cat::getCharacterId, c.getCharacterId())
                .eq(null != c.getLocationId(), Cat::getLocationId, c.getLocationId())
                .eq(null != c.getRecordCount(), Cat::getRecordCount, c.getRecordCount());
        List<Cat> catList = catMapper.selectList(wrapper);
        for (Cat cat : catList) {
            cats.add(catDTOWrapper.wrapCat(cat));
        }
        return cats;
    }

    /**
     * 从临时表中将猫咪添加到正式表
     *
     * @param id 编号
     * @return 状态码
     */
    @Override
    public int addCatFromTemp(long id) {
        /*synchronized (CatLock.get(id)) {
            Cat catTemp = catTempMapper.selectById(id);
            if (catTemp != null) {
                catTempMapper.deleteById(id);
                catTemp.setCatId(null);
                return catMapper.insert(catTemp);
            } else {
                return 0;
            }
        }*/
        CatTemp catTemp = catTempMapper.selectById(id);
        System.out.println(catTemp);
        if (catTemp != null && catTempMapper.updateById(catTemp) == 1) {
            catTempMapper.deleteById(id);
            catTemp.setCatId(null);
            return catMapper.insert(catTemp);
        }
        return 0;
    }

    /**
     * 添加新猫咪
     *
     * @param c     猫咪对象
     * @param isTmp 是否临时表
     * @return 状态码
     */
    @Override
    public int addCat(Cat c, boolean isTmp) {
        return isTmp ? catTempMapper.insert((CatTemp) c) : catMapper.insert(c);
    }

    /**
     * 按id删除某猫咪
     *
     * @param id    编号
     * @param isTmp 是否临时表
     * @return 状态码
     */
    @Override
    public int deleteCatById(long id, boolean isTmp) {
        return isTmp ? catTempMapper.deleteById(id) : catMapper.deleteById(id);
    }

    /**
     * 更新猫咪信息
     *
     * @param c 猫咪对象
     * @return 状态码
     */
    @Override
    public int updateCat(Cat c) {
        LambdaUpdateWrapper<Cat> wrapper = new LambdaUpdateWrapper<>();
        wrapper.set(null != c.getCatName(), Cat::getCatName, c.getCatName())
                .set(null != c.getCategoryId(), Cat::getCategoryId, c.getCategoryId())
                .set(null != c.getColorId(), Cat::getColorId, c.getColorId())
                .set(null != c.getCharacterId(), Cat::getCharacterId, c.getCharacterId())
                .set(null != c.getLocationId(), Cat::getLocationId, c.getLocationId())
                .eq(Cat::getCatId, c.getCatId());
        return catMapper.update(null, wrapper);
    }
}

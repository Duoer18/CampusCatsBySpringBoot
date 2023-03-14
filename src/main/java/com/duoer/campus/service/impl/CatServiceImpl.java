package com.duoer.campus.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.duoer.campus.dao.*;
import com.duoer.campus.dto.CatDTO;
import com.duoer.campus.entity.Cat;
import com.duoer.campus.service.CatService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CatServiceImpl extends ServiceImpl<CatMapper, Cat> implements CatService {
    @Autowired
    private CatMapper catMapper;
    @Autowired
    private CategoryMapper categoryMapper;
    @Autowired
    private CharacterMapper characterMapper;
    @Autowired
    private ColorMapper colorMapper;
    @Autowired
    private LocationMapper locationMapper;

    private CatDTO getDTO(Cat cat) {
        CatDTO catDTO = new CatDTO();
        BeanUtils.copyProperties(cat, catDTO);

        catDTO.setCategory(categoryMapper.selectById(cat.getCategoryId()).getCategory());
        catDTO.setCharacter(characterMapper.selectById(cat.getCharacterId()).getCatCharacter());
        catDTO.setColor(colorMapper.selectById(cat.getColorId()).getColor());
        catDTO.setLocation(locationMapper.selectById(cat.getLocationId()).getLocation());

        return catDTO;
    }

    private List<CatDTO> getDTOList(List<Cat> cats) {
        return cats.stream()
                .map(this::getDTO)
                .collect(Collectors.toList());
    }

    /**
     * 获取所有猫咪
     *
     * @param isTmp 是否临时表
     * @return 所有猫咪集合
     */
    @Override
    public List<CatDTO> getAllCats(boolean isTmp) {
        List<Cat> cats;
        if (isTmp) {
            cats = catMapper.getTmpCats();
        } else {
            cats = list();
        }
        return getDTOList(cats);
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
        Cat cat;
        if (isTmp) {
            cat = catMapper.getTmpCatById(id);
        } else {
            cat = getById(id);
        }
        return getDTO(cat);
    }

    /**
     * 多条件查找
     *
     * @param c 猫咪对象
     * @return 所有猫咪集合
     */
    @Override
    public List<CatDTO> catRandomSearch(Cat c) {
        LambdaQueryWrapper<Cat> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(null != c.getCatName(), Cat::getCatName, c.getCatName())
                .eq(null != c.getCategoryId(), Cat::getCategoryId, c.getCategoryId())
                .eq(null != c.getColorId(), Cat::getColorId, c.getColorId())
                .eq(null != c.getCharacterId(), Cat::getCharacterId, c.getCharacterId())
                .eq(null != c.getLocationId(), Cat::getLocationId, c.getLocationId())
                .eq(null != c.getRecordCount(), Cat::getRecordCount, c.getRecordCount());
        List<Cat> cats = list(wrapper);

        return getDTOList(cats);
    }

    /**
     * 从临时表中将猫咪添加到正式表
     *
     * @param id 编号
     * @return 状态码
     */
    @Override
    public boolean addCatPass(long id) {
        Cat cat = new Cat();
        cat.setCatId(id);
        cat.setDeleted(0);
        cat.setNeedCheck(0);
        return catMapper.addCatPass(id);
    }

    /**
     * 添加新猫咪
     *
     * @param c     猫咪对象
     * @param isTmp 是否临时表
     * @return 状态码
     */
    @Override
    public boolean addCat(Cat c, boolean isTmp) {
        if (isTmp) {
            c.setNeedCheck(1);
            c.setDeleted(1);
        }
        return save(c);
    }

    /**
     * 按id删除某猫咪
     *
     * @param id    编号
     * @param isTmp 是否临时表
     * @return 状态码
     */
    @Override
    public boolean deleteCatById(long id, boolean isTmp) {
        if (isTmp) {
            return catMapper.addCatReject(id);
        } else {
            return removeById(id);
        }
    }

    /**
     * 更新猫咪信息
     *
     * @param c 猫咪对象
     * @return 状态码
     */
    @Override
    public boolean updateCat(Cat c) {
        LambdaUpdateWrapper<Cat> updateWrapper = new LambdaUpdateWrapper<>();
        updateWrapper.set(null != c.getCatName(), Cat::getCatName, c.getCatName())
                .set(null != c.getCategoryId(), Cat::getCategoryId, c.getCategoryId())
                .set(null != c.getColorId(), Cat::getColorId, c.getColorId())
                .set(null != c.getCharacterId(), Cat::getCharacterId, c.getCharacterId())
                .set(null != c.getLocationId(), Cat::getLocationId, c.getLocationId())
                .eq(Cat::getCatId, c.getCatId());
        return update(updateWrapper);
    }
}

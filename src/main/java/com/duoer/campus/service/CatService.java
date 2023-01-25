package com.duoer.campus.service;

import com.duoer.campus.dto.CatDTO;
import com.duoer.campus.entity.Cat;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 猫咪业务接口
 *
 * @author duoer
 */
@Transactional
public interface CatService {
    /**
     * 获取所有猫咪
     *
     * @param isTmp 是否临时表
     * @return 所有猫咪集合
     */
    List<CatDTO> getAllCats(boolean isTmp);

    /**
     * 按id查猫咪
     *
     * @param id    编号
     * @param isTmp 是否临时表
     * @return 猫咪对象
     */
    CatDTO getCatById(long id, boolean isTmp);

    /**
     * 多条件查找
     *
     * @param c 猫咪对象
     * @return 所有猫咪集合
     */
    List<CatDTO> catRandomSearch(Cat c);

    /**
     * 从临时表中将猫咪添加到正式表
     *
     * @param id 编号
     * @return 状态码
     */
    int addCatFromTemp(long id);

    /**
     * 添加新猫咪
     *
     * @param c     猫咪对象
     * @param isTmp 是否临时表
     * @return 状态码
     */
    int addCat(Cat c, boolean isTmp);

    /**
     * 按id删除某猫咪
     *
     * @param id    编号
     * @param isTmp 是否临时表
     * @return 状态码
     */
    int deleteCatById(long id, boolean isTmp);

    /**
     * 更新猫咪信息
     *
     * @param c 猫咪对象
     * @return 状态码
     */
    int updateCat(Cat c);
}

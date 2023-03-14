package com.duoer.campus.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.duoer.campus.dto.CatDTO;
import com.duoer.campus.entity.Cat;

import java.util.List;

/**
 * 猫咪业务接口
 *
 * @author duoer
 */
public interface CatService extends IService<Cat> {
    /**
     * 获取所有猫咪
     *
     * @return 所有猫咪集合
     */
    List<CatDTO> getAllCats();

    /**
     * 按id查猫咪
     *
     * @param id    编号
     * @return 猫咪对象
     */
    CatDTO getCatById(long id);

    /**
     * 多条件查找
     *
     * @param c 猫咪对象
     * @return 所有猫咪集合
     */
    List<CatDTO> catRandomSearch(Cat c);

    /**
     * 按id删除某猫咪
     *
     * @param id    编号
     * @return 状态码
     */
    boolean deleteCatById(long id);

    /**
     * 更新猫咪信息
     *
     * @param c 猫咪对象
     * @return 状态码
     */
    boolean updateCat(Cat c);
}

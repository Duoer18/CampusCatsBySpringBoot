package com.duoer.campus.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.duoer.campus.entity.Cat;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface CatMapper extends BaseMapper<Cat> {
    @Select("select * from cat where need_check = 1")
    List<Cat> getTmpCats();

    @Select("select * from cat where need_check = 1 and cat_id = #{id}")
    Cat getTmpCatById(long id);

    @Update("update cat set deleted = 0, need_check = 0 where cat_id = #{id}")
    boolean addCatPass(long id);

    @Update("update cat set deleted = 1, need_check = 0 where cat_id = #{id}")
    boolean addCatReject(long id);
}

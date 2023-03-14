package com.duoer.campus.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.duoer.campus.entity.CatTemp;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface CatTempMapper extends BaseMapper<CatTemp> {
    @Update("update cat_tmp set deleted = 1, version = version + 1 " +
            "where cat_id = #{id} and deleted = 0 and version = #{version}")
    int myDeleteById(@Param("id") long id, @Param("version") int version);
}

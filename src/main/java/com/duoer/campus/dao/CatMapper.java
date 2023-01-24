package com.duoer.campus.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.duoer.campus.entity.Cat;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CatMapper extends BaseMapper<Cat> {
}

package com.duoer.campus.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.duoer.campus.entity.CatCharacter;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CharacterMapper extends BaseMapper<CatCharacter> {
}

package com.duoer.campus.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.duoer.campus.entity.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper extends BaseMapper<User> {
}

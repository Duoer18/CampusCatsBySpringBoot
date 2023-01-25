package com.duoer.campus.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.duoer.campus.entity.AppearanceRecord;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface AppearanceRecordMapper extends BaseMapper<AppearanceRecord> {
    int deleteAppearanceRecordsByIds(@Param("ids") long[] ids,
                                  @Param("username") String username,
                                  @Param("isAdmin") Boolean isAdmin);
}

package com.duoer.campus.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.duoer.campus.entity.AppearanceRecordTemp;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface AppearanceRecordTempMapper extends BaseMapper<AppearanceRecordTemp> {
    /**
     * 模仿乐观锁机制进行逻辑删除
     */
    @Update("update appearance_record_tmp set deleted = 1, version = version + 1 " +
            "where record_id = #{id} and version = #{version} and deleted = 0")
    int myDeleteById(@Param("id") long id, @Param("version") int version);
}

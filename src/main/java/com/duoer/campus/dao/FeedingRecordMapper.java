package com.duoer.campus.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.duoer.campus.entity.FeedingRecord;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface FeedingRecordMapper extends BaseMapper<FeedingRecord> {
    boolean deleteFeedingRecordsByIds(@Param("ids") long[] ids,
                                  @Param("username") String username);
}

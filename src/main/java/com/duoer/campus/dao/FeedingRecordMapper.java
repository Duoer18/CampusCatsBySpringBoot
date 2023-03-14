package com.duoer.campus.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.duoer.campus.entity.FeedingRecord;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface FeedingRecordMapper extends BaseMapper<FeedingRecord> {
    boolean deleteFeedingRecordsByIds(@Param("ids") long[] ids,
                                  @Param("username") String username,
                                  @Param("isAdmin") Boolean isAdmin);

    @Select("select * from feeding_record where need_check = 1")
    List<FeedingRecord> getTmpRecords();

    @Select("select * from feeding_record where need_check = 1 and record_id = #{id}")
    FeedingRecord getTmpRecordById(long id);

    @Update("update feeding_record set deleted = 0, need_check = 0 where record_id = #{id}")
    boolean addRecordPass(long id);

    @Update("update feeding_record set deleted = 1, need_check = 0 where record_id = #{id}")
    boolean addRecordReject(long id);
}

package com.duoer.campus.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.duoer.campus.dto.FeedingRecordDTO;
import com.duoer.campus.entity.FeedingRecord;

import java.util.List;

public interface FeedingRecordService extends IService<FeedingRecord> {
    /**
     * 获取所有记录
     *
     * @return 所有记录集合
     */
    List<FeedingRecordDTO> getAllRecords();

    /**
     * 获取某用户所有记录
     *
     * @param username 用户名
     * @return 所有记录集合
     */
    List<FeedingRecordDTO> getAllRecordsByUsername(String username);

    /**
     * 按编号查记录
     *
     * @param id   编号
     * @return 记录
     */
    FeedingRecordDTO getRecordById(long id);

    /**
     * 获取某猫咪所有记录
     *
     * @param id   编号
     * @return 所有记录集合
     */
    List<FeedingRecordDTO> getCatOwnRecords(long id);

    /**
     * 修改某记录
     *
     * @param fr 记录对象
     * @return 状态码
     */
    boolean updateRecord(FeedingRecord fr);

    /**
     * 删除某些记录
     *
     * @param ids  id数组
     * @param username 用户名
     * @return 状态码
     */
    boolean deleteRecord(long[] ids, String username);
}

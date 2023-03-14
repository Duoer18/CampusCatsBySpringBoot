package com.duoer.campus.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.duoer.campus.dto.AppearanceRecordDTO;
import com.duoer.campus.entity.AppearanceRecord;

import java.util.List;

public interface AppearanceRecordService extends IService<AppearanceRecord> {
    /**
     * 获取所有记录
     *
     * @return 所有记录集合
     */
    List<AppearanceRecordDTO> getAllRecords();

    /**
     * 获取某用户所有记录
     *
     * @param username 用户名
     * @return 所有记录集合
     */
    List<AppearanceRecordDTO> getAllRecordsByUsername(String username);

    /**
     * 按编号查记录
     *
     * @param id   编号
     * @return 记录
     */
    AppearanceRecordDTO getRecordById(long id);

    /**
     * 获取某猫咪所有记录
     *
     * @param id   编号
     * @return 所有记录集合
     */
    List<AppearanceRecordDTO> getCatOwnRecords(long id);

    /**
     * 修改某记录
     *
     * @param ar 记录对象
     * @return 状态码
     */
    boolean updateRecord(AppearanceRecord ar);

    /**
     * 删除某些记录
     *
     * @param ids  id数组
     * @param username 用户名
     * @return 状态码
     */
    boolean deleteRecord(long[] ids, String username);
}

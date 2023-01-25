package com.duoer.campus.service;

import com.duoer.campus.dto.RecordDTO;
import com.duoer.campus.entity.MyRecord;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 记录业务接口
 *
 * @author duoer
 */
@Transactional
public interface RecordService {
    /**
     * 获取所有记录
     *
     * @param type 类型
     * @return 所有记录集合
     */
    List<? extends RecordDTO> getAllRecords(String type);

    /**
     * 获取某用户所有记录
     *
     * @param username 用户名
     * @param type     类型
     * @return 所有记录集合
     */
    List<? extends RecordDTO> getAllRecordsByUsername(String username, String type);

    /**
     * 获取所有临时表记录
     *
     * @param type 类型
     * @return 所有记录集合
     */
    List<? extends RecordDTO> getAllTempRecords(String type);

    /**
     * 按编号查记录
     *
     * @param id   编号
     * @param type 类型
     * @return 记录
     */
    RecordDTO getRecordById(long id, String type);

    /**
     * 按编号查临时表记录
     *
     * @param id   编号
     * @param type 类型
     * @return 记录
     */
    RecordDTO getTempRecordById(long id, String type);

    /**
     * 获取某猫咪所有记录
     *
     * @param id   编号
     * @param type 类型
     * @return 所有记录集合
     */
    List<? extends RecordDTO> getCatOwnRecords(long id, String type);

    /**
     * 添加新记录
     *
     * @param r 记录对象
     * @return 状态码
     */
    int addRecord(MyRecord r);

    /**
     * 添加临时新记录
     *
     * @param r 记录对象
     * @return 状态码
     */
    int addTempRecord(MyRecord r);

    /**
     * 记录审核通过
     *
     * @param id   临时表id
     * @param type 类型
     * @return 状态码
     */
    int addRecordCheckPass(long id, String type);

    /**
     * 修改某记录
     *
     * @param r 记录对象
     * @return 状态码
     */
    int updateRecord(MyRecord r);

    /**
     * 删除某些记录
     *
     * @param ids  id数组
     * @param type 类型
     * @param username 用户名
     * @param isAdmin 是否管理员
     * @return 状态码
     */
    int deleteRecord(long[] ids, String type, String username, Boolean isAdmin);

    /**
     * 删除某些临时表记录
     *
     * @param ids  id数组
     * @param type 类型
     * @return 状态码
     */
    int deleteTempRecord(List<Long> ids, String type);
}

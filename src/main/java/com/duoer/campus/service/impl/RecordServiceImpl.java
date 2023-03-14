package com.duoer.campus.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.duoer.campus.dao.AppearanceRecordMapper;
import com.duoer.campus.dao.AppearanceRecordTempMapper;
import com.duoer.campus.dao.FeedingRecordMapper;
import com.duoer.campus.dao.FeedingRecordTempMapper;
import com.duoer.campus.dto.AppearanceRecordDTO;
import com.duoer.campus.dto.FeedingRecordDTO;
import com.duoer.campus.dto.RecordDTO;
import com.duoer.campus.dto.wrapper.RecordDTOWrapper;
import com.duoer.campus.entity.*;
import com.duoer.campus.exception.BusinessException;
import com.duoer.campus.service.RecordService;
import com.duoer.campus.response.ResponseCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service
public class RecordServiceImpl implements RecordService {
    @Autowired
    private FeedingRecordMapper feedingRecordMapper;
    @Autowired
    private FeedingRecordTempMapper feedingRecordTempMapper;
    @Autowired
    private AppearanceRecordMapper appearanceRecordMapper;
    @Autowired
    private AppearanceRecordTempMapper appearanceRecordTempMapper;
    @Autowired
    private RecordDTOWrapper recordDTOWrapper;

    private List<FeedingRecordDTO> queryFeeding(LambdaQueryWrapper<FeedingRecord> wrapper) {
        ArrayList<FeedingRecordDTO> records = new ArrayList<>();
        List<FeedingRecord> recordList = feedingRecordMapper.selectList(wrapper);
        for (FeedingRecord fr : recordList) {
            records.add(recordDTOWrapper.wrapFeeding(fr));
        }
        return records;
    }

    private List<AppearanceRecordDTO> queryAppearance(LambdaQueryWrapper<AppearanceRecord> wrapper) {
        ArrayList<AppearanceRecordDTO> records = new ArrayList<>();
        List<AppearanceRecord> recordList = appearanceRecordMapper.selectList(wrapper);
        for (AppearanceRecord ar : recordList) {
            records.add(recordDTOWrapper.wrapAppearance(ar));
        }
        return records;
    }

    /**
     * 获取所有记录
     *
     * @param type 类型
     * @return 所有记录集合
     */
    @Override
    public List<? extends RecordDTO> getAllRecords(String type) {
        if (type.equals("feeding")) {
            return queryFeeding(null);
        } else if (type.equals("appearance")) {
            return queryAppearance(null);
        } else {
            throw new BusinessException(ResponseCode.BUS_ERR.getCode(), "请给出正确的参数");
        }
    }

    /**
     * 获取某用户所有记录
     *
     * @param username 用户名
     * @param type     类型
     * @return 所有记录集合
     */
    @SuppressWarnings("DuplicatedCode")
    @Override
    public List<? extends RecordDTO> getAllRecordsByUsername(String username, String type) {
        if (type.equals("feeding")) {
            LambdaQueryWrapper<FeedingRecord> wrapper = new LambdaQueryWrapper<>();
            wrapper.eq(FeedingRecord::getUsername, username);
            return queryFeeding(wrapper);
        } else if (type.equals("appearance")) {
            LambdaQueryWrapper<AppearanceRecord> wrapper = new LambdaQueryWrapper<>();
            wrapper.eq(AppearanceRecord::getUsername, username);
            return queryAppearance(wrapper);
        } else {
            throw new BusinessException(ResponseCode.BUS_ERR.getCode(), "请给出正确的参数");
        }
    }

    /**
     * 获取所有临时表记录
     *
     * @param type 类型
     * @return 所有记录集合
     */
    @Override
    public List<? extends RecordDTO> getAllTempRecords(String type) {
        if (type.equals("feeding")) {
            ArrayList<FeedingRecordDTO> records = new ArrayList<>();
            List<FeedingRecordTemp> recordList = feedingRecordTempMapper.selectList(null);
            for (FeedingRecordTemp fr : recordList) {
                records.add(recordDTOWrapper.wrapFeeding(fr));
            }
            return records;
        } else if (type.equals("appearance")) {
            ArrayList<AppearanceRecordDTO> records = new ArrayList<>();
            List<AppearanceRecordTemp> recordList = appearanceRecordTempMapper.selectList(null);
            for (AppearanceRecordTemp ar : recordList) {
                records.add(recordDTOWrapper.wrapAppearance(ar));
            }
            return records;
        } else {
            throw new BusinessException(ResponseCode.BUS_ERR.getCode(), "请给出正确的参数");
        }
    }

    /**
     * 按编号查记录
     *
     * @param id   编号
     * @param type 类型
     * @return 记录
     */
    @Override
    public RecordDTO getRecordById(long id, String type) {
        if (type.equals("feeding")) {
            FeedingRecord fr = feedingRecordMapper.selectById(id);
            return recordDTOWrapper.wrapFeeding(fr);
        } else if (type.equals("appearance")) {
            AppearanceRecord ar = appearanceRecordMapper.selectById(id);
            return recordDTOWrapper.wrapAppearance(ar);
        } else {
            throw new BusinessException(ResponseCode.BUS_ERR.getCode(), "请给出正确的参数");
        }
    }

    /**
     * 按编号查临时表记录
     *
     * @param id   编号
     * @param type 类型
     * @return 记录
     */
    @Override
    public RecordDTO getTempRecordById(long id, String type) {
        if (type.equals("feeding")) {
            FeedingRecordTemp fr = feedingRecordTempMapper.selectById(id);
            return recordDTOWrapper.wrapFeeding(fr);
        } else if (type.equals("appearance")) {
            AppearanceRecordTemp ar = appearanceRecordTempMapper.selectById(id);
            return recordDTOWrapper.wrapAppearance(ar);
        } else {
            throw new BusinessException(ResponseCode.BUS_ERR.getCode(), "请给出正确的参数");
        }
    }

    /**
     * 获取某猫咪所有记录
     *
     * @param id   编号
     * @param type 类型
     * @return 所有记录集合
     */
    @SuppressWarnings("DuplicatedCode")
    @Override
    public List<? extends RecordDTO> getCatOwnRecords(long id, String type) {
        if (type.equals("feeding")) {
            LambdaQueryWrapper<FeedingRecord> wrapper = new LambdaQueryWrapper<>();
            wrapper.eq(FeedingRecord::getCatId, id);
            return queryFeeding(wrapper);
        } else if (type.equals("appearance")) {
            LambdaQueryWrapper<AppearanceRecord> wrapper = new LambdaQueryWrapper<>();
            wrapper.eq(AppearanceRecord::getCatId, id);
            return queryAppearance(wrapper);
        } else {
            throw new BusinessException(ResponseCode.BUS_ERR.getCode(), "请给出正确的参数");
        }
    }

    /**
     * 添加新记录
     *
     * @param r 记录对象
     * @return 状态码
     */
    @Override
    public int addRecord(MyRecord r) {
        r.setLastUpdate(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        if (r instanceof FeedingRecord) {
            return feedingRecordMapper.insert((FeedingRecord) r);
        } else if (r instanceof AppearanceRecord){
            return appearanceRecordMapper.insert((AppearanceRecord) r);
        } else {
            throw new BusinessException(ResponseCode.BUS_ERR.getCode(), "请给出正确的参数");
        }
    }

    /**
     * 添加临时新记录
     *
     * @param r 记录对象
     * @return 状态码
     */
    @Override
    public int addTempRecord(MyRecord r) {
        r.setLastUpdate(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        if (r instanceof FeedingRecordTemp) {
            return feedingRecordTempMapper.insert((FeedingRecordTemp) r);
        } else if (r instanceof AppearanceRecordTemp){
            return appearanceRecordTempMapper.insert((AppearanceRecordTemp) r);
        } else {
            throw new BusinessException(ResponseCode.BUS_ERR.getCode(), "请给出正确的参数");
        }
    }

    /**
     * 记录审核通过
     *
     * @param id   临时表id
     * @param type 类型
     * @return 状态码
     */
    @SuppressWarnings("DuplicatedCode")
    @Override
    public int addRecordCheckPass(long id, String type) {
        if (type.equals("feeding")) {
            FeedingRecordTemp fr = feedingRecordTempMapper.selectById(id);
            if (fr != null && feedingRecordTempMapper.myDeleteById(id, fr.getVersion()) == 1) {
                if (fr.getFormerId() == -1) {
                    fr.setRecordId(null);
                    return feedingRecordMapper.insert(fr);
                } else {
                    fr.setRecordId(fr.getFormerId());
                    return updateRecord(fr);
                }
            }
            return 0;
        } else if (type.equals("appearance")) {
            AppearanceRecordTemp ar = appearanceRecordTempMapper.selectById(id);
            if (ar != null && appearanceRecordTempMapper.myDeleteById(id, ar.getVersion()) == 1) {
                if (ar.getFormerId() == -1) {
                    ar.setRecordId(null);
                    return appearanceRecordMapper.insert(ar);
                } else {
                    ar.setRecordId(ar.getFormerId());
                    return updateRecord(ar);
                }
            }
            return 0;
        } else {
            throw new BusinessException(ResponseCode.BUS_ERR.getCode(), "请给出正确的参数");
        }
    }

    /**
     * 修改某记录
     *
     * @param r 记录对象
     * @return 状态码
     */
    @Override
    public int updateRecord(MyRecord r) {
        if (r instanceof FeedingRecord) {
            FeedingRecord fr = (FeedingRecord) r;
            LambdaUpdateWrapper<FeedingRecord> wrapper = new LambdaUpdateWrapper<>();
            wrapper.set(null != fr.getCatId(), FeedingRecord::getCatId, fr.getCatId())
                    .set(null != fr.getLocationId(), FeedingRecord::getLocationId, fr.getLocationId())
                    .set(null != fr.getRecordTime(), FeedingRecord::getRecordTime, fr.getRecordTime())
                    .set(null != fr.getRemarks(), FeedingRecord::getRemarks, fr.getRemarks())
                    .eq(FeedingRecord::getRecordId, fr.getRecordId());
            return feedingRecordMapper.update(null, wrapper);
        } else if (r instanceof AppearanceRecord) {
            AppearanceRecord ar = (AppearanceRecord) r;
            LambdaUpdateWrapper<AppearanceRecord> wrapper = new LambdaUpdateWrapper<>();
            wrapper.set(null != ar.getCatId(), AppearanceRecord::getCatId, ar.getCatId())
                    .set(null != ar.getLocationId(), AppearanceRecord::getLocationId, ar.getLocationId())
                    .set(null != ar.getRecordTime(), AppearanceRecord::getRecordTime, ar.getRecordTime())
                    .eq(AppearanceRecord::getRecordId, ar.getRecordId());
            return appearanceRecordMapper.update(null, wrapper);
        } else {
            throw new BusinessException(ResponseCode.BUS_ERR.getCode(), "请给出正确的参数");
        }
    }

    /**
     * 删除某些记录
     *
     * @param ids      id数组
     * @param type     类型
     * @param username 用户名
     * @param isAdmin  是否管理员
     * @return 状态码
     */
    @Override
    public int deleteRecord(long[] ids, String type, String username, Boolean isAdmin) {
        if (type.equals("feeding")) {
            return feedingRecordMapper.deleteFeedingRecordsByIds(ids, username, isAdmin);
        } else if (type.equals("appearance")) {
            return appearanceRecordMapper.deleteAppearanceRecordsByIds(ids, username, isAdmin);
        } else {
            throw new BusinessException(ResponseCode.BUS_ERR.getCode(), "请给出正确的参数");
        }
    }

    /**
     * 删除某些临时表记录
     *
     * @param ids  id数组
     * @param type 类型
     * @return 状态码
     */
    @Override
    public int deleteTempRecord(List<Long> ids, String type) {
        if (type.equals("feeding")) {
            return feedingRecordTempMapper.deleteBatchIds(ids);
        } else if (type.equals("appearance")) {
            return appearanceRecordTempMapper.deleteBatchIds(ids);
        } else {
            throw new BusinessException(ResponseCode.BUS_ERR.getCode(), "请给出正确的参数");
        }
    }
}

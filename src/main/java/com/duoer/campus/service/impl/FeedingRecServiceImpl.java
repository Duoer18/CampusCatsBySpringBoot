package com.duoer.campus.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.duoer.campus.dao.CatMapper;
import com.duoer.campus.dao.FeedingRecordMapper;
import com.duoer.campus.dao.LocationMapper;
import com.duoer.campus.dto.FeedingRecordDTO;
import com.duoer.campus.entity.FeedingRecord;
import com.duoer.campus.service.FeedingRecordService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class FeedingRecServiceImpl extends ServiceImpl<FeedingRecordMapper, FeedingRecord>
        implements FeedingRecordService {
    @Autowired
    private FeedingRecordMapper feedingRecordMapper;
    @Autowired
    private CatMapper catMapper;
    @Autowired
    private LocationMapper locationMapper;

    private FeedingRecordDTO getDTO(FeedingRecord fr) {
        FeedingRecordDTO feedingRecordDTO = new FeedingRecordDTO();
        BeanUtils.copyProperties(fr, feedingRecordDTO);

        feedingRecordDTO.setCatName(catMapper.selectById(fr.getCatId()).getCatName());
        feedingRecordDTO.setLocation(locationMapper.selectById(fr.getLocationId()).getLocation());
        return feedingRecordDTO;
    }

    private List<FeedingRecordDTO> getDTOList(List<FeedingRecord> feedingRecords) {
        return feedingRecords.stream()
                .map(this::getDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<FeedingRecordDTO> getAllRecords() {
        List<FeedingRecord> feedingRecords = list();
        return getDTOList(feedingRecords);
    }

    @Override
    public List<FeedingRecordDTO> getAllRecordsByUsername(String username) {
        LambdaQueryWrapper<FeedingRecord> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(FeedingRecord::getUsername, username);
        return getDTOList(list(queryWrapper));
    }

    @Override
    public FeedingRecordDTO getRecordById(long id) {
        FeedingRecord fr = getById(id);
        return getDTO(fr);
    }

    @Override
    public List<FeedingRecordDTO> getCatOwnRecords(long id) {
        LambdaQueryWrapper<FeedingRecord> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(FeedingRecord::getCatId, id);
        return getDTOList(list(queryWrapper));
    }

    @Override
    public boolean updateRecord(FeedingRecord fr) {
        LambdaUpdateWrapper<FeedingRecord> updateWrapper = new LambdaUpdateWrapper<>();
        updateWrapper.set(null != fr.getCatId(), FeedingRecord::getCatId, fr.getCatId())
                .set(null != fr.getLocationId(), FeedingRecord::getLocationId, fr.getLocationId())
                .set(null != fr.getRecordTime(), FeedingRecord::getRecordTime, fr.getRecordTime())
                .set(null != fr.getRemarks(), FeedingRecord::getRemarks, fr.getRemarks())
                .eq(FeedingRecord::getRecordId, fr.getRecordId());
        return update(updateWrapper);
    }

    @Override
    public boolean deleteRecord(long[] ids, String username) {
        return feedingRecordMapper.deleteFeedingRecordsByIds(ids, username);
    }
}

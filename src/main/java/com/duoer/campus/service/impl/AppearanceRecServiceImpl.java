package com.duoer.campus.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.duoer.campus.dao.AppearanceRecordMapper;
import com.duoer.campus.dao.CatMapper;
import com.duoer.campus.dao.LocationMapper;
import com.duoer.campus.dto.AppearanceRecordDTO;
import com.duoer.campus.entity.AppearanceRecord;
import com.duoer.campus.service.AppearanceRecordService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AppearanceRecServiceImpl extends ServiceImpl<AppearanceRecordMapper, AppearanceRecord>
        implements AppearanceRecordService {
    @Autowired
    private AppearanceRecordMapper appearanceRecordMapper;
    @Autowired
    private CatMapper catMapper;
    @Autowired
    private LocationMapper locationMapper;

    private AppearanceRecordDTO getDTO(AppearanceRecord ar) {
        AppearanceRecordDTO appearanceRecordDTO = new AppearanceRecordDTO();
        BeanUtils.copyProperties(ar, appearanceRecordDTO);

        appearanceRecordDTO.setCatName(catMapper.selectById(ar.getCatId()).getCatName());
        appearanceRecordDTO.setLocation(locationMapper.selectById(ar.getLocationId()).getLocation());
        return appearanceRecordDTO;
    }

    private List<AppearanceRecordDTO> getDTOList(List<AppearanceRecord> appearanceRecords) {
        return appearanceRecords.stream()
                .map(this::getDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<AppearanceRecordDTO> getAllRecords() {
        List<AppearanceRecord> appearanceRecords = list();
        return getDTOList(appearanceRecords);
    }

    @Override
    public List<AppearanceRecordDTO> getAllRecordsByUsername(String username) {
        LambdaQueryWrapper<AppearanceRecord> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(AppearanceRecord::getUsername, username);
        return getDTOList(list(queryWrapper));
    }

    @Override
    public AppearanceRecordDTO getRecordById(long id) {
        AppearanceRecord ar = getById(id);
        return getDTO(ar);
    }

    @Override
    public List<AppearanceRecordDTO> getCatOwnRecords(long id) {
        LambdaQueryWrapper<AppearanceRecord> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(AppearanceRecord::getCatId, id);
        return getDTOList(list(queryWrapper));
    }

    @Override
    public boolean updateRecord(AppearanceRecord ar) {
        LambdaUpdateWrapper<AppearanceRecord> updateWrapper = new LambdaUpdateWrapper<>();
        updateWrapper.set(null != ar.getCatId(), AppearanceRecord::getCatId, ar.getCatId())
                .set(null != ar.getLocationId(), AppearanceRecord::getLocationId, ar.getLocationId())
                .set(null != ar.getRecordTime(), AppearanceRecord::getRecordTime, ar.getRecordTime())
                .eq(AppearanceRecord::getRecordId, ar.getRecordId());
        return update(updateWrapper);
    }

    @Override
    public boolean deleteRecord(long[] ids, String username) {
        return appearanceRecordMapper.deleteAppearanceRecordsByIds(ids, username);
    }
}
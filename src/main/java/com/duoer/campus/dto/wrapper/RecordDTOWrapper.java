package com.duoer.campus.dto.wrapper;

import com.duoer.campus.dao.CatMapper;
import com.duoer.campus.dao.LocationMapper;
import com.duoer.campus.dto.AppearanceRecordDTO;
import com.duoer.campus.dto.FeedingRecordDTO;
import com.duoer.campus.entity.AppearanceRecord;
import com.duoer.campus.entity.AppearanceRecordTemp;
import com.duoer.campus.entity.FeedingRecord;
import com.duoer.campus.entity.FeedingRecordTemp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RecordDTOWrapper {
    @Autowired
    private CatMapper catMapper;
    @Autowired
    private LocationMapper locationMapper;

    public FeedingRecordDTO wrapFeeding(FeedingRecord fr) {
        return new FeedingRecordDTO(
                fr.getRecordId(),
                fr.getCatId(),
                catMapper.selectById(fr.getCatId()).getCatName(),
                fr.getUsername(),
                fr.getLocationId(),
                locationMapper.selectById(fr.getLocationId()).getLocation(),
                fr.getRecordTime(),
                fr.getLastUpdate(),
                fr.getRemarks(),
                fr instanceof FeedingRecordTemp ? ((FeedingRecordTemp) fr).getFormerId() : null
        );
    }

    public AppearanceRecordDTO wrapAppearance(AppearanceRecord ar) {
        return new AppearanceRecordDTO(
                ar.getRecordId(),
                ar.getCatId(),
                catMapper.selectById(ar.getCatId()).getCatName(),
                ar.getUsername(),
                ar.getLocationId(),
                locationMapper.selectById(ar.getLocationId()).getLocation(),
                ar.getRecordTime(),
                ar.getLastUpdate(),
                ar instanceof AppearanceRecordTemp ? ((AppearanceRecordTemp) ar).getFormerId() : null
        );
    }
}

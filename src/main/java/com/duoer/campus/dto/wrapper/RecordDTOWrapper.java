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
                 );
    }

    public AppearanceRecordDTO wrapAppearance(AppearanceRecord ar) {
        return new AppearanceRecordDTO(
                );
    }
}

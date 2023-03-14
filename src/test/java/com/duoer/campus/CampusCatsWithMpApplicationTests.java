package com.duoer.campus;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.duoer.campus.dao.AppearanceRecordMapper;
import com.duoer.campus.dao.AppearanceRecordTempMapper;
import com.duoer.campus.dao.CatMapper;
import com.duoer.campus.entity.*;
import com.duoer.campus.service.CatService;
import com.duoer.campus.service.RecordService;
import com.duoer.campus.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.net.InetAddress;
import java.net.UnknownHostException;

@SpringBootTest
class CampusCatsWithMpApplicationTests {
    @Autowired
    private RecordService recordService;
    @Autowired
    private UserService userService;
    @Autowired
    private CatService catService;
    @Autowired
    private AppearanceRecordTempMapper appearanceRecordTempMapper;
    @Autowired
    private AppearanceRecordMapper appearanceRecordMapper;

    @Test
    void contextLoads() throws UnknownHostException {
        System.out.println(InetAddress.getLocalHost().getHostAddress());
    }

    @Test
    void getRecord() {
        System.out.println(recordService.getAllRecords("feeding"));
        System.out.println("==================================================================");
        System.out.println(recordService.getAllRecordsByUsername("kkkkkk", "feeding"));
        System.out.println("==================================================================");
        System.out.println(recordService.getAllTempRecords("feeding"));
    }

    @Test
    void updRecord() {
        FeedingRecord fr = new FeedingRecord();
        fr.setRecordId(1L);
        fr.setLocationId(1L);
        recordService.updateRecord(fr);
    }

    @Test
    void login() {
        User u = new User();
        u.setUsername("kkkkkk");
        u.setPassword("666666");
        System.out.println(userService.loginCheck(u));
        u.setPassword("6666666");
        System.out.println(userService.loginCheck(u));
    }

    @Test
    void addCatPass() {
//        catService.addCatFromTemp(10);
        System.out.println(catService.deleteCatById(100L, true));
    }

    @Test
    void delRecordTemp() {
//        appearanceRecordTempMapper.deleteBatchIds();
//        AppearanceRecordTemp appearanceRecordTemp = new AppearanceRecordTemp();
//        appearanceRecordTemp.setRecordId(2L);
//        appearanceRecordTemp.setFormerId(-1L);
//        appearanceRecordTemp.setVersion(0);
//        System.out.println(appearanceRecordTempMapper.updateById(appearanceRecordTemp));
        AppearanceRecordTemp ar = appearanceRecordTempMapper.selectById(7L);
        appearanceRecordMapper.insert(ar);
    }

    @Test
    void catInsert() {
        Cat cat = new Cat(-1L, "1", 1L, 1L, 1L, 1L,
                null, 1, 1);
        catService.save(cat);

        System.out.println(catService.list());
    }

    @Test
    void catUpdate() {
        catService.addCatPass(-1);
        LambdaQueryWrapper<Cat> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Cat::getDeleted, 1);
        System.out.println(catService.list(queryWrapper));
//        System.out.println(catService.getAllCats(true));
//        LambdaUpdateWrapper<Cat> updateWrapper = new LambdaUpdateWrapper<>();
//        updateWrapper.set(Cat::getDeleted, 1)
//                .eq(Cat::getCatId, -1L);
//        catService.update(updateWrapper);
    }
}

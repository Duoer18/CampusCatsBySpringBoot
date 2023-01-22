package com.duoer.campus;

import com.duoer.campus.dao.CatMapper;
import com.duoer.campus.entity.Cat;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class CampusCatsApplicationTests {

    @Autowired
    private CatMapper catMapper;
    @Test
    void contextLoads() {
        Cat c = new Cat();
        c.setCatId(1);
        c.setCatName("咪咪");
        List<Cat> cats = catMapper.randomSelect(c);
        System.out.println(cats);
    }

}

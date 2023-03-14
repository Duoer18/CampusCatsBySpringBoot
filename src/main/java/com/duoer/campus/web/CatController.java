package com.duoer.campus.web;

import com.duoer.campus.dto.CatDTO;
import com.duoer.campus.entity.Cat;
import com.duoer.campus.service.CatService;
import com.duoer.campus.response.ResponseCode;
import com.duoer.campus.response.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cats")
public class CatController {
    @Autowired
    private CatService catService;

    /**
     * 添加新猫咪
     *
     * @param c 前端传递猫咪对象
     * @return 状态码
     */
    @PostMapping
    public Result addCat(@RequestBody Cat c) {
        boolean saved = catService.save(c);

        int code = saved ? ResponseCode.ADD_SUC.getCode() : ResponseCode.ADD_ERR.getCode();
        String msg = saved ? "" : "添加猫咪失败！";
        return new Result(code, saved, msg);
    }

    /**
     * 获取所有猫咪
     *
     * @return 所有猫咪集合
     */
    @GetMapping
    public Result getAllCats() {
        List<CatDTO> cats = catService.getAllCats();
        int code = cats != null ? ResponseCode.GET_SUC.getCode() : ResponseCode.GET_ERR.getCode();
        String msg = cats != null ? "" : "猫咪数据查询失败！";
        return new Result(code, cats, msg);
    }

    /**
     * 按id获取猫咪信息
     *
     * @param id 编号
     * @return 猫咪对象
     */
    @GetMapping("/{id}")
    public Result getCatById(@PathVariable long id) {
        CatDTO cat = catService.getCatById(id);
        int code = cat != null ? ResponseCode.GET_SUC.getCode() : ResponseCode.GET_ERR.getCode();
        String msg = cat != null ? "" : "猫咪数据查询失败！";
        return new Result(code, cat, msg);
    }
}

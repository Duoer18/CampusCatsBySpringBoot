package com.duoer.campus.web;

import com.duoer.campus.dto.CatDTO;
import com.duoer.campus.entity.AppearanceRecord;
import com.duoer.campus.entity.Cat;
import com.duoer.campus.entity.FeedingRecord;
import com.duoer.campus.entity.MyRecord;
import com.duoer.campus.service.AppearanceRecordService;
import com.duoer.campus.service.CatService;
import com.duoer.campus.service.FeedingRecordService;
import com.duoer.campus.response.ResponseCode;
import com.duoer.campus.response.Result;
import com.duoer.campus.utils.ResultGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private CatService catService;
    @Autowired
    private FeedingRecordService feedingRecordService;
    @Autowired
    private AppearanceRecordService appearanceRecordService;

    /**
     * 获取所有待审核猫咪信息
     *
     * @return 所有猫咪集合
     */
    @GetMapping("/cats")
    public Result getAllTempCats() {
        List<CatDTO> cats = catService.getAllCats(true);
        int code = cats != null ? ResponseCode.GET_SUC.getCode() : ResponseCode.GET_ERR.getCode();
        String msg = cats != null ? "" : "猫咪数据查询失败！";
        return new Result(code, cats, msg);
    }

    /**
     * 猫咪添加申请通过
     *
     * @param id 临时表id
     * @return 状态码
     */
    @GetMapping("/cats/{id}")
    public Result addCatCheckPass(@PathVariable long id) {
        boolean updated = catService.addCatPass(id);
        int code = updated ? ResponseCode.ADD_SUC.getCode() : ResponseCode.ADD_ERR.getCode();
        String msg = updated ? "" : "添加猫咪失败！";
        return new Result(code, updated, msg);
    }

    /**
     * 直接删除猫咪
     *
     * @param id 编号
     * @return 状态码
     */
    @DeleteMapping("/cats/{id}")
    public Result deleteCat(@PathVariable long id) {
        boolean deleted = catService.deleteCatById(id, false);
        int code = deleted ? ResponseCode.DEL_SUC.getCode() : ResponseCode.DEL_ERR.getCode();
        String msg = deleted ? "" : "删除猫咪失败！";
        return new Result(code, deleted, msg);
    }

    /**
     * 驳回添加猫咪申请
     *
     * @param id 临时表id
     * @return 状态码
     */
    @DeleteMapping("/cats/tmp/{id}")
    public Result addCatCheckReject(@PathVariable long id) {
        boolean deleted = catService.deleteCatById(id, true);
        int code = deleted ? ResponseCode.DEL_SUC.getCode() : ResponseCode.DEL_ERR.getCode();
        String msg = deleted ? "" : "删除猫咪失败！";
        return new Result(code, deleted, msg);
    }

    /**
     * 修改猫咪信息
     *
     * @param c 前端传递猫咪对象
     * @return 状态码
     */
    @PutMapping("/cats")
    public Result updateCat(@RequestBody Cat c) {
        boolean updated = catService.updateCat(c);
        int code = updated ? ResponseCode.UPD_SUC.getCode() : ResponseCode.UPD_ERR.getCode();
        String msg = updated ? "" : "修改猫咪失败！";
        return new Result(code, updated, msg);
    }

    /**
     * 获取所有待审核记录
     * @param type 类型
     * @return 所有记录集合
     */
    @GetMapping("/records/{type}")
    public Result getAllTempRecords(@PathVariable String type) {
        List<? extends MyRecord> records = null;
        if (type.equals("feeding")) {
            records = feedingRecordService.getAllRecords(true);
        } else if (type.equals("appearance")) {
            records = appearanceRecordService.getAllRecords(true);
        }

        return ResultGenerator.getResult(records);
    }

    /**
     * 通过记录请求申请
     * @param id 临时表id
     * @param type 类型
     * @return 状态码
     */
    @GetMapping("/records/{type}/{id}")
    public Result addRecordCheckPass(@PathVariable long id, @PathVariable String type) {
        boolean updated = false;
        if (type.equals("feeding")) {
            updated = feedingRecordService.addRecordCheckPass(id);
        } else if (type.equals("appearance")) {
            updated = appearanceRecordService.addRecordCheckPass(id);
        }

        int code = updated ? ResponseCode.ADD_SUC.getCode() : ResponseCode.ADD_ERR.getCode();
        String msg = updated ? "" : "添加记录失败！";
        return new Result(code, updated, msg);
    }

    /**
     * 驳回记录请求申请
     * @param record 记录
     * @return 状态码
     */
    @DeleteMapping("/records")
    public Result addRecordCheckReject(@RequestBody MyRecord record) {
        boolean updated = false;
        if (record instanceof FeedingRecord) {
            updated = feedingRecordService.addRecordReject(record.getRecordId());
        } else if (record instanceof AppearanceRecord) {
            updated = appearanceRecordService.addRecordReject(record.getRecordId());
        }
        int code = updated ? ResponseCode.DEL_SUC.getCode() : ResponseCode.DEL_ERR.getCode();
        String msg = updated ? "" : "删除记录失败！";
        return new Result(code, updated, msg);
    }
}

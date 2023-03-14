package com.duoer.campus.web;

import com.duoer.campus.BaseContext;
import com.duoer.campus.dto.FeedingRecordDTO;
import com.duoer.campus.entity.FeedingRecord;
import com.duoer.campus.entity.User;
import com.duoer.campus.service.FeedingRecordService;
import com.duoer.campus.response.ResponseCode;
import com.duoer.campus.response.Result;
import com.duoer.campus.utils.ResultGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/feedings")
public class FeedingRecordController {
    @Autowired
    private FeedingRecordService feedingRecordService;

    /**
     * 获取所有记录
     *
     * @return 所有记录集合
     */
    @GetMapping
    public Result getAllRecords() {
        List<FeedingRecordDTO> records = feedingRecordService.getAllRecords(false);
        return ResultGenerator.getResult(records);
    }

    /**
     * 获取某用户所有记录
     *
     * @return 所有记录集合
     */
    @GetMapping("/user")
    public Result getAllRecordsByUsername() {
        User user = BaseContext.get();
        String username =user.getUsername();
        List<FeedingRecordDTO> records = feedingRecordService.getAllRecordsByUsername(username);
        return ResultGenerator.getResult(records);
    }

    /**
     * 添加新投喂记录
     *
     * @param fr 前端传递记录对象
     * @return 状态码
     */
    @PostMapping
    public Result addRecord(@RequestBody FeedingRecord fr) {
        System.out.println(fr);

        User user = BaseContext.get();
        String username =user.getUsername();
        Boolean isAdmin = user.getIsAdmin();
        isAdmin = isAdmin != null ? isAdmin : false;

        fr.setRecordId(null);
        fr.setUsername(username);
        boolean saved = feedingRecordService.addRecord(fr, !isAdmin);

        int code = saved ? ResponseCode.ADD_SUC.getCode() : ResponseCode.ADD_ERR.getCode();
        String msg = saved ? "" : "添加记录失败！";
        return new Result(code, saved, msg);
    }

    /**
     * 获取猫咪全部记录
     *
     * @param id 猫咪id
     * @return 所有记录集合
     */
    @GetMapping("/cat/{id}")
    public Result catRecords(@PathVariable long id) {
        List<FeedingRecordDTO> records = feedingRecordService.getCatOwnRecords(id);
        return ResultGenerator.getResult(records);
    }

    /**
     * 删除所选记录
     *
     * @param ids id数组
     * @return 状态码
     */
    @DeleteMapping
    public Result deleteRecord(@RequestBody long[] ids) {
        System.out.println(Arrays.toString(ids));
        User user = BaseContext.get();
        String username =user.getUsername();
        Boolean isAdmin = user.getIsAdmin();
        isAdmin = isAdmin != null ? isAdmin : false;
        boolean deleted = feedingRecordService.deleteRecord(ids, username, isAdmin);
        String msg = deleted ? "" : "删除记录失败！";
        int code = deleted ? ResponseCode.DEL_SUC.getCode() : ResponseCode.DEL_ERR.getCode();
        return new Result(code, deleted, msg);
    }

    /**
     * 更新某记录
     *
     * @param fr 前端传递记录对象
     * @return 状态码
     */
    @PutMapping
    public Result updateRecord(@RequestBody FeedingRecord fr) {
        User user = BaseContext.get();
        fr.setUsername(user.getUsername());
        Boolean isAdmin = user.getIsAdmin();
        isAdmin = isAdmin != null ? isAdmin : false;
        boolean updated = feedingRecordService.updateRecord(fr, !isAdmin);

        int code = updated ? ResponseCode.UPD_SUC.getCode() : ResponseCode.UPD_ERR.getCode();
        String msg = updated ? "" : "更新记录失败！";
        return new Result(code, updated, msg);
    }

    /**
     * 获取待修改记录的信息
     *
     * @param id 编号
     * @return 该记录对象
     */
    @GetMapping("/{id}")
    public Result PreUpdateRecord(@PathVariable long id) {
        FeedingRecordDTO record = feedingRecordService.getRecordById(id, false);
        int code = record != null ? ResponseCode.GET_SUC.getCode() : ResponseCode.GET_ERR.getCode();
        String msg = record != null ? "" : "记录数据查询失败！";
        return new Result(code, record, msg);
    }
}

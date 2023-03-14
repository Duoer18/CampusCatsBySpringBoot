package com.duoer.campus.web;

import com.duoer.campus.BaseContext;
import com.duoer.campus.dto.RecordDTO;
import com.duoer.campus.entity.FeedingRecord;
import com.duoer.campus.entity.FeedingRecordTemp;
import com.duoer.campus.entity.User;
import com.duoer.campus.service.RecordService;
import com.duoer.campus.response.ResponseCode;
import com.duoer.campus.response.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/feedings")
public class FeedingRecordController {
    @Autowired
    private RecordService recordService;

    /**
     * 获取所有记录
     *
     * @return 所有记录集合
     */
    @GetMapping
    public Result getAllRecords() {
        List<? extends RecordDTO> records = recordService.getAllRecords("feeding");
        int code = records != null ? ResponseCode.GET_SUC.getCode() : ResponseCode.GET_ERR.getCode();
        String msg = records != null ? "" : "记录数据查询失败！";
        return new Result(code, records, msg);
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
        List<? extends RecordDTO> records = recordService.getAllRecordsByUsername(username, "feeding");
        int code = records != null ? ResponseCode.GET_SUC.getCode() : ResponseCode.GET_ERR.getCode();
        String msg = records != null ? "" : "记录数据查询失败！";
        return new Result(code, records, msg);
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
        fr.setRecordId(null);
        fr.setUsername(username);
        isAdmin = isAdmin != null ? isAdmin : false;
        int status = isAdmin ?
                recordService.addRecord(fr) :
                recordService.addTempRecord(new FeedingRecordTemp(fr, -1L));
        int code = status == 1 ? ResponseCode.ADD_SUC.getCode() : ResponseCode.ADD_ERR.getCode();
        String msg = status == 1 ? "" : "添加记录失败！";
        return new Result(code, status, msg);
    }

    /**
     * 获取猫咪全部记录
     *
     * @param id 猫咪id
     * @return 所有记录集合
     */
    @GetMapping("/cat/{id}")
    public Result catRecords(@PathVariable long id) {
        List<? extends RecordDTO> records = recordService.getCatOwnRecords(id, "feeding");
        int code = records != null ? ResponseCode.GET_SUC.getCode() : ResponseCode.GET_ERR.getCode();
        String msg = records != null ? "" : "记录数据查询失败！";
        return new Result(code, records, msg);
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
        int status = recordService.deleteRecord(ids, "feeding", username, isAdmin);
        int code = status >= 1 ? ResponseCode.DEL_SUC.getCode() : ResponseCode.DEL_ERR.getCode();
        String msg = status >= 1 ? "" : "删除记录失败！";
        return new Result(code, status, msg);
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
        String username =user.getUsername();
        Boolean isAdmin = user.getIsAdmin();
        fr.setUsername(username);

        int status;
        isAdmin = isAdmin != null ? isAdmin : false;
        if (isAdmin) {
            status = recordService.updateRecord(fr);
        } else {
            status = recordService.addTempRecord(new FeedingRecordTemp(fr, fr.getRecordId()));
        }

        int code = status == 1 ? ResponseCode.UPD_SUC.getCode() : ResponseCode.UPD_ERR.getCode();
        String msg = status == 1 ? "" : "更新记录失败！";
        return new Result(code, status, msg);
    }

    /**
     * 获取待修改记录的信息
     *
     * @param id 编号
     * @return 该记录对象
     */
    @GetMapping("/{id}")
    public Result PreUpdateRecord(@PathVariable long id) {
        RecordDTO record = recordService.getRecordById(id, "feeding");
        int code = record != null ? ResponseCode.GET_SUC.getCode() : ResponseCode.GET_ERR.getCode();
        String msg = record != null ? "" : "记录数据查询失败！";
        return new Result(code, record, msg);
    }
}

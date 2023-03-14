package com.duoer.campus.web;

import com.duoer.campus.BaseContext;
import com.duoer.campus.dto.RecordDTO;
import com.duoer.campus.entity.AppearanceRecord;
import com.duoer.campus.entity.AppearanceRecordTemp;
import com.duoer.campus.entity.User;
import com.duoer.campus.service.RecordService;
import com.duoer.campus.response.ResponseCode;
import com.duoer.campus.response.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/appearances")
public class AppearanceRecordController {
    @Autowired
    private RecordService recordService;

    /**
     * 获取所有记录
     *
     * @return 所有记录集合
     */
    @GetMapping
    public Result getAllRecords() {
        List<? extends RecordDTO> records = recordService.getAllRecords("appearance");
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
        List<? extends RecordDTO> records = null;
        if (username != null) {
            records = recordService.getAllRecordsByUsername(username, "appearance");
        }
        int code = records != null ? ResponseCode.GET_SUC.getCode() : ResponseCode.GET_ERR.getCode();
        String msg = records != null ? "" : "记录数据查询失败！";
        return new Result(code, records, msg);
    }

    /**
     * 添加新出现记录
     *
     * @param ar 前端传递记录对象
     * @return 状态码
     */
    @PostMapping
    public Result addRecord(@RequestBody AppearanceRecord ar) {
        User user = BaseContext.get();
        String username =user.getUsername();
        Boolean isAdmin = user.getIsAdmin();
        ar.setUsername(username);
        isAdmin = isAdmin != null ? isAdmin : false;
        int status = isAdmin ?
                recordService.addRecord(ar) :
                recordService.addTempRecord(new AppearanceRecordTemp(ar, -1L));
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
        List<? extends RecordDTO> records = recordService.getCatOwnRecords(id, "appearance");
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
        username = username != null ? username : "";
        isAdmin = isAdmin != null ? isAdmin : false;
        int status = recordService.deleteRecord(ids, "appearance", username, isAdmin);
        int code = status >= 1 ? ResponseCode.DEL_SUC.getCode() : ResponseCode.DEL_ERR.getCode();
        String msg = status >= 1 ? "" : "删除记录失败！";
        return new Result(code, status, msg);
    }

    /**
     * 更新某记录
     *
     * @param ar 前端传递记录对象
     * @return 状态码
     */
    @PutMapping
    public Result updateRecord(@RequestBody AppearanceRecord ar) {
        User user = BaseContext.get();
        String username =user.getUsername();
        Boolean isAdmin = user.getIsAdmin();
        ar.setUsername(username);

        int status;
        isAdmin = isAdmin != null ? isAdmin : false;
        if (isAdmin) {
            status = recordService.updateRecord(ar);
        } else {
            status = recordService.addTempRecord(new AppearanceRecordTemp(ar, ar.getRecordId()));
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
        RecordDTO record = recordService.getRecordById(id, "appearance");
        int code = record != null ? ResponseCode.GET_SUC.getCode() : ResponseCode.GET_ERR.getCode();
        String msg = record != null ? "" : "记录数据查询失败！";
        return new Result(code, record, msg);
    }
}

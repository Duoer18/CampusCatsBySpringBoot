package com.duoer.campus.web;

import com.duoer.campus.BaseContext;
import com.duoer.campus.dto.AppearanceRecordDTO;
import com.duoer.campus.entity.AppearanceRecord;
import com.duoer.campus.entity.User;
import com.duoer.campus.service.AppearanceRecordService;
import com.duoer.campus.response.ResponseCode;
import com.duoer.campus.response.Result;
import com.duoer.campus.utils.ResultGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/appearances")
public class AppearanceRecordController {
    @Autowired
    private AppearanceRecordService appearanceRecordService;

    /**
     * 获取所有记录
     *
     * @return 所有记录集合
     */
    @GetMapping
    public Result getAllRecords() {
        List<AppearanceRecordDTO> records = appearanceRecordService.getAllRecords(false);
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
        List<AppearanceRecordDTO> records = null;
        if (username != null) {
            records = appearanceRecordService.getAllRecordsByUsername(username);
        }
        return ResultGenerator.getResult(records);
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
        boolean saved = appearanceRecordService.addRecord(ar, !isAdmin);
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
        List<AppearanceRecordDTO> records = appearanceRecordService.getCatOwnRecords(id);
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
        username = username != null ? username : "";
        isAdmin = isAdmin != null ? isAdmin : false;
        boolean deleted = appearanceRecordService.deleteRecord(ids, username, isAdmin);
        int code = deleted ? ResponseCode.DEL_SUC.getCode() : ResponseCode.DEL_ERR.getCode();
        String msg = deleted ? "" : "删除记录失败！";
        return new Result(code, deleted, msg);
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

        isAdmin = isAdmin != null ? isAdmin : false;
        boolean updated = appearanceRecordService.updateRecord(ar, !isAdmin);

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
        AppearanceRecordDTO record = appearanceRecordService.getRecordById(id, false);
        int code = record != null ? ResponseCode.GET_SUC.getCode() : ResponseCode.GET_ERR.getCode();
        String msg = record != null ? "" : "记录数据查询失败！";
        return new Result(code, record, msg);
    }
}

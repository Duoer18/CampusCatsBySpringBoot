package com.duoer.campus.utils;

import com.duoer.campus.dto.AppearanceRecordDTO;
import com.duoer.campus.entity.MyRecord;
import com.duoer.campus.response.ResponseCode;
import com.duoer.campus.response.Result;

import java.util.List;

public class ResultGenerator {
    public static Result getResult(List<? extends MyRecord> records) {
        int code = records != null ? ResponseCode.GET_SUC.getCode() : ResponseCode.GET_ERR.getCode();
        String msg = records != null ? "" : "记录数据查询失败！";
        return new Result(code, records, msg);
    }
}

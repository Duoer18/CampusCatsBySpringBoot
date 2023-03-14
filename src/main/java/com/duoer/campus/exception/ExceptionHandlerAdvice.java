package com.duoer.campus.exception;

import com.duoer.campus.response.ResponseCode;
import com.duoer.campus.response.Result;
import io.jsonwebtoken.JwtException;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.ModelAndView;

import java.sql.SQLException;

@ControllerAdvice
public class ExceptionHandlerAdvice {
    @ExceptionHandler(SystemException.class)
    @ResponseBody
    public Result sysHandle(SystemException e) {
        // notify the exception
        e.printStackTrace();
        return new Result(e.getCode(), null, e.getMessage());
    }

    @ExceptionHandler(BusinessException.class)
    @ResponseBody
    public Result busHandle(BusinessException e) {
        return new Result(e.getCode(), null, e.getMessage());
    }

    /**
     * 参数类型不匹配异常、请求体缺失异常，采用与业务异常相似的处理，提示用户输入正确的参数
     *
     * @return Result对象
     */
    @ExceptionHandler({MethodArgumentTypeMismatchException.class, HttpMessageNotReadableException.class})
    @ResponseBody
    public Result argMismatch() {
        return new Result(ResponseCode.BUS_ERR.getCode(), null, "请给出正确参数！");
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    @ResponseBody
    public Result noSupportMethod() {
        return new Result(ResponseCode.BUS_ERR.getCode(), null, "错误的请求方式！");
    }

    @ExceptionHandler(SQLException.class)
    @ResponseBody
    public Result sqlException() {
        return new Result(ResponseCode.SYS_ERR.getCode(), null, "系统繁忙，请稍后！");
    }

    @ExceptionHandler({JwtException.class, IllegalArgumentException.class})
    public String jwt() {
        return "/home";
    }

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public Result handle(Exception e) {
        e.printStackTrace();
        return new Result(ResponseCode.ERR.getCode(), null, "未知的错误！");
    }
}

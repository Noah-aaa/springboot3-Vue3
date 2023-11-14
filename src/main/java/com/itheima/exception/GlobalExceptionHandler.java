package com.itheima.exception;

import com.itheima.pojo.Result;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

/**
 * @author longteng
 * @date 2023/11/12 21:29
 **/
//@ResponseBody
//@ControllerAdvice
@RestControllerAdvice // 异常处理器注解
public class GlobalExceptionHandler {
    @ExceptionHandler(Exception.class)
    public Result handlerException(Exception e){
        e.printStackTrace();
        return Result.error(StringUtils.hasLength(e.getMessage())? e.getMessage() : "操作失败");
    }
}

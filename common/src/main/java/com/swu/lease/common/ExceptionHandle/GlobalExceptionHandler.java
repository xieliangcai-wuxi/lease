package com.swu.lease.common.ExceptionHandle;

import com.swu.lease.common.result.Result;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
//全局处理异常
@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public Result handle(Exception e) {
        e.printStackTrace();
        return Result.fail();
    }

    @ExceptionHandler(value = LeaseException.class)
    @ResponseBody
    public Result handle(LeaseException e) {
        e.printStackTrace();
        Integer code = e.getCode();
        String message = e.getMessage();
        return Result.fail(code, message);
    }
}

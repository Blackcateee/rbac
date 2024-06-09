package com.zmxstudy.rbac.handler;

import com.zmxstudy.rbac.vo.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Controller中统一异常处理
 *
 * @author star
 */
@Slf4j
@ResponseBody
@ControllerAdvice
public class ControllerExceptionHandler {
    /**
     * 通用异常处理
     *
     * @param e 异常对象
     * @return /
     */
    @ExceptionHandler(Exception.class)
    public Result<Object> exceptionHander(Exception e) {
        log.error(e.getMessage());
        e.printStackTrace();
        return Result.systemError();
    }
}

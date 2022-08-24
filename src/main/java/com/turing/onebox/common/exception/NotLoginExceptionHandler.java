package com.turing.onebox.common.exception;

import cn.dev33.satoken.exception.NotLoginException;
import com.turing.onebox.common.utils.AjaxJson;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @ClassName NotLoginExceptionHandler
 * @Description TODO
 * @Author Colin
 * @Date 2022/8/24 15:54
 * @Version 1.0
 */
@RestControllerAdvice
public class NotLoginExceptionHandler {

    @ExceptionHandler
    public AjaxJson<?> handlerException(NotLoginException nle){

        return AjaxJson.getNotLogin();

    }


}

package com.example.coursedesign.exception;


import com.example.coursedesign.entity.Result;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.ShiroException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.sql.SQLIntegrityConstraintViolationException;

@Slf4j
@RestControllerAdvice  //全局捕获 异步
public class GlobalExceptionHandler {

    @ResponseStatus(HttpStatus.BAD_REQUEST)  //返回异常状态
    @ExceptionHandler(value = RuntimeException.class) //异常类型
    public Result handler(RuntimeException e){
        log.error("运行时异常--------",e);
        return Result.fail(e.getMessage());
    }

    @ResponseStatus(HttpStatus.UNAUTHORIZED)  //异常状态:未授权
    @ExceptionHandler(value = ShiroException.class) //异常类型
    public Result handler(ShiroException e){
        log.error("运行时异常--------",e);
        return Result.result(401,e.getMessage(),null);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)  //返回异常状态
    @ExceptionHandler(value = MethodArgumentNotValidException.class) //异常类型
    public Result handler(MethodArgumentNotValidException e){
        log.error("实体校验异常,格式不满足--------",e);
        return Result.fail(e.getMessage());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)  //返回异常状态
    @ExceptionHandler(value = SQLIntegrityConstraintViolationException.class) //异常类型
    public Result handler(SQLIntegrityConstraintViolationException e){
        log.error("不满足数据库约束--------",e);
        return Result.fail("账号已存在 请重新注册");
    }

    //Assert
    @ResponseStatus(HttpStatus.UNAUTHORIZED)  //异常状态:未授权
    @ExceptionHandler(value = IllegalArgumentException.class) //异常类型
    public Result handler(IllegalArgumentException e){
        log.error("Assert异常--------",e);
        return Result.fail("权限不足");
    }

}

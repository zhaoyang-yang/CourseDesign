package com.example.coursedesign.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class Result {
    private int code;
    private String msg;
    private Object data;

    public static Result result(int code,String msg,Object data){
        Result r = new Result();
        r.setCode(code);
        r.setMsg(msg);
        r.setData(data);
        return r;
    }

    public static Result succ(Object data){
        return Result.result(200,"访问成功",data);
    }

    public static Result fail(String msg){
        return Result.result(400,msg,null);
    }
}

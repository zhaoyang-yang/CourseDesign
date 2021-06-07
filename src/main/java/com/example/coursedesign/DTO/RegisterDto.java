package com.example.coursedesign.DTO;


import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class RegisterDto {
    @NotNull(message = "账号不能为空")
    private long id;
    @NotNull(message = "用户名不能为空")
    private String name;
    @NotNull(message = "密码不能为空")
    private String password;
    @NotNull(message = "用户类型不能为空")
    private int type;
    private String company;
}

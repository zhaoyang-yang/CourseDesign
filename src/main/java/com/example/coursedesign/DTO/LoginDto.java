package com.example.coursedesign.DTO;


import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
public class LoginDto {
    @NotNull(message = "用户名不能为空")
    long id;
    @NotBlank(message = "密码不能为空")
    String password;
    @NotNull(message = "用户类型不能为空")
    int usertype;
}

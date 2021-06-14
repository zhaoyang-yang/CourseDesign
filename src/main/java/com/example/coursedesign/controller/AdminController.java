package com.example.coursedesign.controller;

import cn.hutool.core.map.MapUtil;
import com.example.coursedesign.DTO.ChoseDto;
import com.example.coursedesign.DTO.LoginDto;
import com.example.coursedesign.DTO.RegisterDto;
import com.example.coursedesign.Untils.JwtUtil;
import com.example.coursedesign.Untils.ShiroUntil;
import com.example.coursedesign.entity.Mydate;
import com.example.coursedesign.entity.Predete;
import com.example.coursedesign.entity.Result;
import com.example.coursedesign.entity.User;
import com.example.coursedesign.service.impl.AdminService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.util.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
@RequestMapping("/admin/")
public class AdminController {

    @Autowired
    AdminService adminService;

    //获取教师信息
    @GetMapping("getteacher")
    public Result getteacher(){
        List<User> users = adminService.getTeachers();
        return Result.succ(users);
    }


    @PostMapping("addteacher")
    @RequiresAuthentication
    public Result addteacher(@RequestBody @Validated User user){
        adminService.addTeacher(user);
        return Result.succ(adminService.getTeachers());
    }

    @PostMapping("updatateacher")
    @RequiresAuthentication
    public Result updatateacher(@RequestBody @Validated User user){
        adminService.updataTeacher(user);
        return Result.succ(adminService.getTeachers());
    }

    @PostMapping("delteacher")
    @RequiresAuthentication
    public Result delteacher(long id){
        adminService.delTeacher(id);
        return Result.succ(adminService.getTeachers());
    }
}

package com.example.coursedesign.controller;

import com.example.coursedesign.DTO.LabDto;
import com.example.coursedesign.entity.Result;
import com.example.coursedesign.entity.User;
import com.example.coursedesign.service.impl.AdminService;
import com.example.coursedesign.service.impl.UserService;
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

    //获取教师信息s
    @GetMapping("getteachers")
    public Result getteacher(){
        List<User> users = adminService.getTeachers();
        return Result.succ(users);
    }

    //添加教师
    @PostMapping("addteacher")
    @RequiresAuthentication
    public Result addteacher(@RequestBody @Validated User user){
        adminService.addTeacher(user);
        return Result.succ(adminService.getTeachers());
    }

    //更新教师信息
    @PostMapping("updatateacher")
    @RequiresAuthentication
    public Result updatateacher(@RequestBody User user){
        adminService.updataTeacher(user);
        return Result.succ(adminService.getTeachers());
    }

    //删除教师
    @GetMapping("delteacher")
    @RequiresAuthentication
    public Result delteacher(long id){
        adminService.delTeacher(id);
        return Result.succ(adminService.getTeachers());
    }

    //添加实验室
    @PostMapping("addlab")
    @RequiresAuthentication
    public Result addlab(@RequestBody LabDto labDto){
        List<LabDto> labs = adminService.getlabs();
        for (LabDto lab : labs) {
            if (lab.getLabid()==labDto.getLabid())
                return Result.fail("已存在实验室");
        }
        adminService.addlab(labDto);
        return Result.succ(adminService.getlabs());
    }

    //获取实验室
    @GetMapping("getlabs")
    public Result getlabs(){
        return Result.succ(adminService.getlabs());
    }

    //删除实验室
    @GetMapping("dellab")
    @RequiresAuthentication
    public Result dellab(Integer labid){
        adminService.dellab(labid);
        return Result.succ(adminService.getlabs());
    }
}

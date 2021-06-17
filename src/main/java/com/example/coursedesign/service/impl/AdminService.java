package com.example.coursedesign.service.impl;

import com.example.coursedesign.DTO.LabDto;
import com.example.coursedesign.entity.User;
import com.example.coursedesign.mapper.Adminmapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminService {

    @Autowired
    Adminmapper adminmapper;

    //获取教师
    public List<User> getTeachers() {
        return adminmapper.getteachers();
    }

    //修改教师信息
    public void updataTeacher(User user) {
        adminmapper.updateteacher(user);
    }

    //插入教师
    public void addTeacher(User user) {
        adminmapper.insertteacher(user);
    }

    //删除教师
    public void delTeacher(long id){
        adminmapper.delteacher(id);
    }

    //增加实验室
    public void addlab(LabDto labDto){
        adminmapper.addlab(labDto);
    }

    //获取实验室
    public List<LabDto> getlabs(){
        List<LabDto> labs = adminmapper.getlabs();
        return labs;
    }

    //删除实验室
    public void dellab(int id){
        adminmapper.dellab(id);
    }
}


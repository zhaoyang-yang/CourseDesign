package com.example.coursedesign.service.impl;

import com.example.coursedesign.entity.Mydate;
import com.example.coursedesign.entity.Predete;
import com.example.coursedesign.entity.User;
import com.example.coursedesign.mapper.Usermapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;

@Service
public class UserService {

    @Autowired
    Usermapper usermapper;

    //根据id获取用户数据
    public User getUserbyId(long id){
        return usermapper.getuserByid(id);
    }

    //插入用户、注册
    public String insertUser(User user){try {
        usermapper.insertuser(user);
        return null;
    }catch (Exception e){
        return e.toString();
    }
    }

    //更新用户信息
    public void updateuser(Long userid){
        User user = getUserbyId(userid);
        usermapper.updateuser(user);
    }

    //获取课程
    public List<String> getCourse(){
        return usermapper.getcourse();
    }

    //根据labid查询实验室占用
    public List<Mydate> selectPretimeByLabid(int labid){
        return usermapper.selectpretimebylabid(labid);
    }

    //预约实验室
    public void choseLab(Predete predete){
        usermapper.choselab(predete);
    }

    //获取所有实验室id
    public List<Integer> getlabs(){
        return usermapper.getlabs();
    }

    //通过用户id查询实验室占用
    public List<Predete> selectPredeteByUserid(long userid){
        return usermapper.selectpredetebyuserid(userid);
    }

    //用户id和week查询
    public List<Predete> selectLabByWeek(int week,long userid){
        return usermapper.selectlabbyweek(week,userid);
    }

    //用户id和实验室id查询
    public List<Predete> selectPredeteByTwoId(long userid,int labid){
        return usermapper.selectpredetebytwoid(userid,labid);
    }

    //取消实验室预约
    public void cancelpre(Predete predete){
        usermapper.cancelpre(predete);
    }

}

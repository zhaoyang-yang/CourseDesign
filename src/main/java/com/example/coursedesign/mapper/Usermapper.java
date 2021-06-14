package com.example.coursedesign.mapper;

import com.example.coursedesign.entity.Mydate;
import com.example.coursedesign.entity.Predete;
import com.example.coursedesign.entity.User;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.List;

@Mapper
@Repository
public interface Usermapper {

    //通过id获取用户
    @Select("select * from user where id = #{id}")
    public User getuserByid(@Param("id") long id);

    //插入用户
    @Insert("insert into user(id, name, company,password,type) VALUES (#{id},#{name},#{company},#{password},#{type})")
    public void insertuser(User user);

    //更新用户信息
    @Update("update user set password = #{password},name = #{name},company = #{company} where id = #{userid}")
    public void updateuser(User user);

    //获取实验室占用情况
    @Select("select week,day,time from predete where labid=#{labid}")
    public List<Mydate> selectpretimebylabid(@Param("labid") int labid);

    //获取用户选的实验室
    @Select("select * from predete where userid = #{userid}")
    public List<Predete> selectpredetebyuserid(@Param("userid") long userid);

    //根据星期和用户id查实验室
    @Select("select labid,week,day,time from predete where week=#{week} and userid=#{userid}")
    public List<Mydate> selectlabbyweek(@Param("week") int week,@Param("userid") long userid);

    //根据userid和labid查询
    @Select("select * from predete where userid=#{userid} and labid=#{labid}")
    public List<Predete> selectpredetebytwoid(@Param("userid") long userid,@Param("labid") int labid);

    //退选实验室
    @Delete("delete from predete where userid=#{userid} and labid=#{labid} and week=#{week} and day=#{day} and time=#{time}")
    public void cancelpre(Predete predete);

    //选用实验室
    @Insert("insert into predete(labid, userid, username, pretime,week,day,time) VALUES (#{labid},#{userid},#{username},#{pretime},#{week},#{day},#{time})")
    public void choselab(Predete predete);

    //获取所有实验室id
    @Select("select labid from laboratory")
    public List<Integer> getlabs();
}

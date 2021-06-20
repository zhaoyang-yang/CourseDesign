package com.example.coursedesign.mapper;

import com.example.coursedesign.DTO.LabDto;
import com.example.coursedesign.entity.User;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.List;

@Mapper
@Repository
public interface Adminmapper {
    //获取所有教师
    @Select("select * from user where type in (1,-1)")
    public List<User> getteachers();

    //修改教师信息
    @Update("update user set type = #{type} where id = #{id}")
    public void updateteacher(User user);

    //插入教师
    @Insert("insert into user(id, name, company,password,type) VALUES (#{id},#{name},#{company},#{password},#{type})")
    public void insertteacher(User user);

    //删除教师
    @Delete("delete from user where id = #{id}")
    public void delteacher(@Param("id") long id);

    //增加实验室
    @Insert("insert into laboratory(labid, mac_number) VALUES (#{labid},#{macnumber})")
    public void addlab(LabDto labDto);

    //获取实验室信息
    @Select("select * from laboratory")
    public List<LabDto> getlabs();

    //删除实验室
    @Delete("delete from laboratory where labid = #{id}")
    public void dellab(@Param("id") int id);
}

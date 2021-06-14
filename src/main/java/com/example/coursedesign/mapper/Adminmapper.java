package com.example.coursedesign.mapper;

import com.example.coursedesign.entity.User;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.List;

@Mapper
@Repository
public interface Adminmapper {
    //获取所有教师
    @Select("select * from user where type=1")
    public List<User> getteachers();

    //修改教师信息
    @Update("update user set name = #{name} and company = #{company} where id = #{userid}")
    public void updateteacher(User user);

    //插入教师
    @Insert("insert into user(id, name, company,password,type) VALUES (#{id},#{name},#{company},#{password},#{type})")
    public void insertteacher(User user);

    //删除教师
    @Delete("delete from user where id = #{id}")
    public void delteacher(@Param("id") long id);
}

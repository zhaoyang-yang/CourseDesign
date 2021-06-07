package com.example.coursedesign.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.sql.Date;

@Data
@TableName("predete")

public class Predete {

    private String username;
    @NotNull(message = "实验室id不能为空")
    private int labid;
    @NotNull(message = "用户id不能为空")
    private long userid;

    private Date pretime;
    @NotNull(message = "预约时间格式不正确")
    private int week;
    @NotNull(message = "预约时间格式不正确")
    private String day;
    @NotNull(message = "预约时间格式不正确")
    private int time;

}

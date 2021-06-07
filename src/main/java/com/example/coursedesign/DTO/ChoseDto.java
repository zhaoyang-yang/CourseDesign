package com.example.coursedesign.DTO;

import com.example.coursedesign.entity.Mydate;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.sql.Date;
import java.util.List;

@Data
public class ChoseDto {
    private String username;
    @NotNull(message = "实验室编号不能为空")
    private int labid;

    private long userid;

    private List<Date> pretimes;
    @NotNull
    private int week;
    @NotNull
    private String day;
    @NotNull
    private int time;
}

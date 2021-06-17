package com.example.coursedesign.DTO;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class LabDto {
    @NotNull
    private int labid;
    @NotNull
    private int macnumber;
}

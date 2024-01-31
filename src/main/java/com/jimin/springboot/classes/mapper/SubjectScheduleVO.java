package com.jimin.springboot.classes.mapper;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SubjectScheduleVO{
    private String subName;
    private String roomName;
    private String dayId;
    private String day;
    private String timeId;
    private String startTime;
    private String endTime;
}

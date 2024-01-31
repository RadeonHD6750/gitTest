package com.jimin.springboot.classes.mapper;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ClassesMapper {

    //강의 목록
    List<SubjectVO> selectSubjectList();

    //특정 강의 스케줄
    List<SubjectScheduleVO> selectSubjectScheduleList(SubjectVO subjectVo);
}

package com.jimin.springboot.classes.service;

import com.jimin.springboot.classes.mapper.ClassesMapper;
import com.jimin.springboot.classes.mapper.ClassesScheduleVO;
import com.jimin.springboot.classes.mapper.SubjectScheduleVO;
import com.jimin.springboot.classes.mapper.SubjectVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ClassesService {

    private final ClassesMapper classesMapper;


    @Autowired
    public ClassesService(ClassesMapper classesMapper) {
        this.classesMapper = classesMapper;
    }

    public List<ClassesScheduleVO> selectClassesScheduleList()
    {
        List<ClassesScheduleVO> result = new ArrayList<>();
        List<SubjectVO> subjectList = selectSubjectList();

        for (SubjectVO sub : subjectList)
        {
            ClassesScheduleVO temp = new ClassesScheduleVO();

            temp.setSubId(sub.getSubId());
            temp.setSubName(sub.getSubName());
            temp.setProName(sub.getProName());

            String spaceTimeTemp = "";
            List<SubjectScheduleVO> subScheduleList = selectSubjectScheduleList(sub);
            for (SubjectScheduleVO schedule :subScheduleList)
            {
                spaceTimeTemp += "[" + schedule.getRoomName() + "|";
                spaceTimeTemp += schedule.getDay() + "|";
                spaceTimeTemp += schedule.getStartTime() + "]  ";

            }

            temp.setSpaceTime(spaceTimeTemp);

            result.add(temp);
        }

        return result;
    }

    public List<SubjectVO> selectSubjectList()
    {
        return classesMapper.selectSubjectList();
    }

    public List<SubjectScheduleVO> selectSubjectScheduleList(SubjectVO subjectVo)
    {
        return classesMapper.selectSubjectScheduleList(subjectVo);
    }
}

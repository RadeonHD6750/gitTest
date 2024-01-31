package com.jimin.springboot.departments.mapper;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;


@Mapper
public interface DepartmentMapper {
    DepartmentVO isDepartment(int department_id);

    List<DepartmentVO> selectDepartmentList();
}

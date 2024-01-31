package com.jimin.springboot.employees.mapper;


import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface EmployeeMapper {
    EmployeeVO selectEmployeeOne(int employee_id);

    List<EmployeeExtentionVO> selectEmployeeList(int department_id);

    //사원등록
    void insertEmployeeOne(EmployeeVO employeeVO);

    //사원갱신
    void updateEmployeeOne(EmployeeVO employeeVO);

    void deleteEmployeeOne(EmployeeVO employeeVO);
}

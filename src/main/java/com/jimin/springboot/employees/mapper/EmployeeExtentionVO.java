package com.jimin.springboot.employees.mapper;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EmployeeExtentionVO extends EmployeeVO{
    private int departmentId;
    private String departmentName;
    private String parentDepartmentName;
}

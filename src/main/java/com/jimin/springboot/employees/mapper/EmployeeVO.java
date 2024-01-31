package com.jimin.springboot.employees.mapper;


import lombok.Getter;
import lombok.Setter;

import java.sql.Date;

@Getter
@Setter
//사용자 VO
public class EmployeeVO {
    private int employeeId;
    private String empName;
    private String email;
    private String phoneNumber;
    private Date hireDate;
    private double salary;
    private int managerId;
    private int departmentId;

    private Date createDate;
    private Date updateDate;
}

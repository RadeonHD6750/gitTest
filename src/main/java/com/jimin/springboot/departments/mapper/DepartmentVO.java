package com.jimin.springboot.departments.mapper;


import lombok.Getter;
import lombok.Setter;

import java.sql.Date;

@Getter
@Setter
public class DepartmentVO {
    private int departmentId;
    private String departmentName;
    private String parentDepartmentName;
    private int parentId;
    private int managerId;

    private Date createDate;
    private Date updateDate;
}

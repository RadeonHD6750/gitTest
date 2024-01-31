package com.jimin.springboot.departments.service;

import com.jimin.springboot.departments.mapper.DepartmentMapper;
import com.jimin.springboot.departments.mapper.DepartmentVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartmentService {

    private final DepartmentMapper departmentMapper;

    @Autowired
    public DepartmentService(DepartmentMapper departmentMapper) {
        this.departmentMapper = departmentMapper;
    }


    public DepartmentVO isDepartment(int department_id) throws Exception
    {
        return departmentMapper.isDepartment(department_id);
    }

    public List<DepartmentVO> selectDepartmentList() throws Exception
    {
        return departmentMapper.selectDepartmentList();
    }

}

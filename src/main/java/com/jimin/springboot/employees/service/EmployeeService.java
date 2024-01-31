package com.jimin.springboot.employees.service;

import com.jimin.springboot.employees.mapper.EmployeeExtentionVO;
import com.jimin.springboot.employees.mapper.EmployeeMapper;
import com.jimin.springboot.employees.mapper.EmployeeVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {


    private final EmployeeMapper employeeMapper;

    @Autowired
    public EmployeeService(EmployeeMapper employeeMapper) {
        this.employeeMapper = employeeMapper;
    }

    public EmployeeVO selectEmployeeOne(int employee_id) throws Exception
    {
        return employeeMapper.selectEmployeeOne(employee_id);
    }

    public List<EmployeeExtentionVO> selectEmployeeList(int department_id) throws Exception
    {
        return employeeMapper.selectEmployeeList(department_id);
    }


    public void insertEmployeeOne(EmployeeVO employeeVO) throws Exception
    {
        employeeMapper.insertEmployeeOne(employeeVO);
    }

    public void updateEmployeeOne(EmployeeVO employeeVO) throws Exception
    {
        employeeMapper.updateEmployeeOne(employeeVO);
    }

    public void deleteEmployeeOne(EmployeeVO employeeVO) throws Exception
    {
        employeeMapper.deleteEmployeeOne(employeeVO);
    }


}

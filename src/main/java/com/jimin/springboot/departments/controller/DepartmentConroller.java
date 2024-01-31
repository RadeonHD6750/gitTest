package com.jimin.springboot.departments.controller;

import com.jimin.springboot.departments.mapper.DepartmentVO;
import com.jimin.springboot.departments.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class DepartmentConroller {

    private final DepartmentService departmentService;

    @Autowired
    public DepartmentConroller(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }


    @ResponseBody
    @RequestMapping(value = "/isDepartment", method = RequestMethod.POST)
    public DepartmentVO isDepartment(@RequestBody DepartmentVO departmentVO) throws Exception
    {
        System.out.println("isDepartment");

        int department_id = departmentVO.getDepartmentId();
        System.out.println(department_id);

        return departmentService.isDepartment(department_id);

    }

    @ResponseBody
    @RequestMapping(value = "/dept/list", method = RequestMethod.POST)
    public List<DepartmentVO> selectDepartmentList() throws Exception
    {
        List<DepartmentVO> departmentVOList = departmentService.selectDepartmentList();

        return departmentVOList;
    }
}

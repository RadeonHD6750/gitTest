package com.jimin.springboot.employees.controller;

import com.jimin.springboot.employees.service.EmployeeService;
import com.jimin.springboot.security.service.SecurityService;
import com.jimin.springboot.employees.mapper.EmployeeExtentionVO;
import com.jimin.springboot.employees.mapper.EmployeeVO;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@Controller
public class EmployeeController {

    private final SecurityService securityService;
    private final EmployeeService employeeService;


    @Autowired
    public EmployeeController(SecurityService securityService, EmployeeService employeeService) {
        this.securityService = securityService;
        this.employeeService = employeeService;
    }


    @GetMapping(value="/emp/list")
    public String list(HttpServletRequest request, Model model, SpringDataWebProperties.Pageable pageable) throws Exception
    {
        System.out.println("EmployeeController");
        return securityService.forwardLoginProtect(request, "emp/emp-list");
    }


    //VO로 통신
    @ResponseBody
    @RequestMapping(value = "/emp/one", method = RequestMethod.POST)
    public EmployeeVO SELECT_EMPLOYEE_ONEPOSTJSONVO(@RequestBody EmployeeVO employeeVO) throws Exception
    {
        return employeeService.selectEmployeeOne(employeeVO.getEmployeeId());
    }

    @ResponseBody
    @RequestMapping(value = "/emp/list", method = RequestMethod.POST)
    public List<EmployeeExtentionVO> selectEmployeeList(@RequestBody HashMap<String, Object> map) throws Exception
    {
        System.out.println("selectEmployeeList");

        int department_id = (int) map.get("departmentId");

        System.out.println("departmentId " + department_id);

        List<EmployeeExtentionVO> employeeExtentionVOList = employeeService.selectEmployeeList(department_id);

        return employeeExtentionVOList;
    }

    @ResponseBody
    @RequestMapping(value = "/emp/insert", method = RequestMethod.POST)
    public HashMap<String, Object> insertEmployeeOne(@RequestBody EmployeeVO employeeVO) throws Exception
    {
        System.out.println("insertEmployeeOne");

        System.out.println(employeeVO.getEmployeeId());
        System.out.println(employeeVO.getEmpName());

        HashMap<String, Object> result_map = new HashMap<>();

        String result = "등록성공";


        employeeService.insertEmployeeOne(employeeVO);

        result_map.put("result_code", 200);
        result_map.put("result_data", result);

        return result_map;
    }


    @ResponseBody
    @RequestMapping(value = "/emp/update", method = RequestMethod.POST)
    public HashMap<String, Object> updateEmployeeOne(@RequestBody EmployeeVO employeeVO) throws Exception
    {
        System.out.println("updateEmployeeOne");

        HashMap<String, Object> result_map = new HashMap<>();

        String result = "갱신성공";

        try
        {
            employeeService.updateEmployeeOne(employeeVO);
        }
        catch (Exception e)
        {
            result = e.getMessage();
            System.out.println(result);

        }

        result_map.put("result_code", 200);
        result_map.put("result_data", result);

        return result_map;
    }


    @ResponseBody
    @RequestMapping(value = "/emp/delete", method = RequestMethod.POST)
    public HashMap<String, Object> deleteEmployeeOne(@RequestBody EmployeeVO employeeVO) throws Exception
    {
        System.out.println("deleteEmployeeOne");

        HashMap<String, Object> result_map = new HashMap<>();

        String result = "삭제성공";

        try
        {
            employeeService.deleteEmployeeOne(employeeVO);
        }
        catch (Exception e)
        {
            result = e.getMessage();
            System.out.println(result);

        }

        result_map.put("result_code", 200);
        result_map.put("result_data", result);

        return result_map;
    }
}

package com.jimin.springboot.classes.controller;

import com.jimin.springboot.classes.mapper.ClassesScheduleVO;
import com.jimin.springboot.classes.service.ClassesService;
import com.jimin.springboot.security.service.SecurityService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.awt.print.Pageable;
import java.lang.reflect.Method;
import java.util.List;

@Controller
public class ClassesController
{
    private final SecurityService securityService;

    private final ClassesService classesService;

    @Autowired
    public ClassesController(SecurityService securityService, ClassesService classesService) {
        this.securityService = securityService;
        this.classesService = classesService;
    }

    //전체 조회
    @RequestMapping(value="/classes/list", produces="text/plain;charset=UTF-8", method = RequestMethod.GET)
    public String viewList(HttpServletRequest request, Model model)
    {
        List<ClassesScheduleVO> classesScheduleVOList = classesService.selectClassesScheduleList();
        model.addAttribute("scheduleList", classesScheduleVOList);
        return securityService.forwardLoginProtect(request, "classes/classes-list");
    }

    @ResponseBody
    @PostMapping(value="/classes/list")
    public List<ClassesScheduleVO> boardlist(HttpServletRequest request)
    {
        return classesService.selectClassesScheduleList();
    }
}

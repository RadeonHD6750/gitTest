package com.jimin.springboot.sensor.controller;


import com.jimin.springboot.security.service.SecurityService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SensorController {

    private final SecurityService securityService;

    @Autowired
    public SensorController(SecurityService securityService) {
        this.securityService = securityService;
    }

    @GetMapping(value="/sensor/list")
    public String home(HttpServletRequest request) throws Exception
    {
        System.out.println("home controller");

        return securityService.forwardLoginProtect(request, "sensor/monitoring");
    }
}

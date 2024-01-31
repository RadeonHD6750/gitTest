package com.jimin.springboot.home.controller;


import com.jimin.springboot.security.service.SecurityService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    private final SecurityService securityService;

    public HomeController(SecurityService securityService) {
        this.securityService = securityService;
    }

    @GetMapping(value="/home")
    public String home(HttpServletRequest request) throws Exception
    {
        System.out.println("home controller");

        return securityService.forwardLoginProtect(request, "home");
    }
}

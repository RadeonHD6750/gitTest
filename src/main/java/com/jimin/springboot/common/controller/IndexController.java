package com.jimin.springboot.common.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController
{
    private final String trySession = "redirect:/session-login";
    @GetMapping(value = {"", "/"})
    public String index()
    {
        return trySession;
    }
}

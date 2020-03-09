package com.community.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LandscapeGardeningController {
    @GetMapping("/sjly")
    public String sjly(){
        return "sjly";
    }
}

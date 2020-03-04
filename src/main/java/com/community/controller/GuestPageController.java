package com.community.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class GuestPageController {
    @GetMapping("/guest")
    public String home(){
        return "guest";
    }
}

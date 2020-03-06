package com.community.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AbountUsController {
    @GetMapping("/zwyjj")
    public String zwyjj(){
        return "zwyjj";
    }

    @GetMapping("/zzjg")
    public String zzjg(){
        return "zzjg";
    }

    @GetMapping("/zwyry")
    public String zwyry(){
        return "zwyry";
    }

    @GetMapping("/contactus")
    public String contactus(){
        return "contactus";
    }
}

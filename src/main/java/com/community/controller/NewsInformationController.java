package com.community.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class NewsInformationController {

    @GetMapping("/zxgg")
    public String zxgg(){
        return "zxgg";
    }

    @GetMapping("/yndt")
    public String yndt(){
        return "yndt";
    }

    @GetMapping("/dzzq")
    public String dzzq(){
        return "dzzq";
    }
}

package com.community.controller;

import com.community.model.Garden;
import com.community.model.News;
import com.community.model.Science;
import com.community.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class DelController {

    @Autowired
    private NewsService newsService;

    @Autowired
    private ScienceService scienceService;

    @Autowired
    private EducationService educationService;

    @Autowired
    private GardenService gardenService;

    @Autowired
    private Questionservice questionservice;

    @GetMapping("/delete")
    public String delete(@RequestParam("id") Long id,
                         @RequestParam("contenttype") Integer contenttype
                         ){
        switch (contenttype) {
            case 1:
                newsService.delbyId(id);
                break;
            case 2:
                scienceService.delById(id);
                break;
            case 3:
                educationService.delById(id);
                break;
            case 4:
                gardenService.delById(id);
                break;
            default:
        }

        return "redirect:/home";
    }

    @GetMapping("/del")
    public String del(@RequestParam("id") Long id
    ){
        questionservice.delById(id);
        return "redirect:/";
    }
}

package com.community.controller;

import com.community.model.Education;
import com.community.model.Garden;
import com.community.model.News;
import com.community.service.EducationService;
import com.community.service.GardenService;
import com.community.service.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.management.modelmbean.ModelMBean;
import java.util.List;

@Controller
public class HomeController {

    @Autowired
    private NewsService newsService;

    @Autowired
    private EducationService educationService;

    @Autowired
    private GardenService gardenService;

    @GetMapping("/home")
    public String home(Model model){
        List<News> news = newsService.homepage();
        List<Education> educations = educationService.homepage();
        List<Garden> gardens = gardenService.homepage();

        model.addAttribute("news",news);
        model.addAttribute("educations",educations);
        model.addAttribute("gardens",gardens);

        return "home";
    }
}

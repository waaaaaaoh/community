package com.community.controller;

import com.community.model.Education;
import com.community.service.EducationService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class EducationController {

    @Autowired
    private EducationService educationService;

    @GetMapping("/education/{id}")
    public String educationModel(Model model,
                                 @PathVariable(name = "id") Integer type,
                                 @RequestParam(defaultValue = "1") int pageNum,
                                 @RequestParam(defaultValue = "6") int pageSize) {
        String title = null;
        PageInfo<Education> news = educationService.listByType(type, pageNum, pageSize);
        switch (type) {
            case 1:
                title = "教育项目";
                break;
            case 2:
                title = "活动纪实";
                break;
            case 3:
                title = "志愿者";
                break;
            case 4:
                title = "活动招募";
                break;
            case 5:
                title = "大话植物";
                break;
            case 6:
                title = "科普馆";
                break;
            default:
        }
        model.addAttribute("news", news);
        model.addAttribute("type", type);
        model.addAttribute("title", title);
        return "education";
    }

}

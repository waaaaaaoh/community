package com.community.controller;

import com.community.model.Science;
import com.community.service.ScienceService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ScientificResearchController {

    @Autowired
    ScienceService scienceService;

    @GetMapping("/science/{id}")
    public String science(Model model,
                          @PathVariable(name = "id") Integer type,
                          @RequestParam(defaultValue = "1") int pageNum,
                          @RequestParam(defaultValue = "6") int pageSize) {
        String title = null;
        PageInfo<Science> articles = scienceService.listByType(type, pageNum, pageSize);
        switch (type) {
            case 1:
                title = "芙蓉研究";
                break;
            case 2:
                title = "种质资源";
                break;
            case 3:
                title = "合作交流";
                break;
            case 4:
                title = "园林有害生物预测报告";
                break;
            default:
        }
        model.addAttribute("articles", articles);
        model.addAttribute("type", type);
        model.addAttribute("title",title);
        return "science";
    }
}

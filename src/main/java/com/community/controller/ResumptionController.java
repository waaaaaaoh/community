package com.community.controller;

import com.community.model.*;
import com.community.service.*;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class ResumptionController {

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

    @GetMapping("/resumption/{contenttype}")
    public String resumption(@PathVariable(name = "contenttype") Integer contentType,
                             Model model,
                             @RequestParam(defaultValue = "1") int pageNum,
                             @RequestParam(defaultValue = "5") int pageSize){
        switch (contentType){
            case 1:
                PageInfo<News> nlist = newsService.delList(pageNum,pageSize);
                model.addAttribute("delList",nlist);
                model.addAttribute("name","新闻动态");
                break;
            case 2:
                PageInfo<Science> slist = scienceService.delList(pageNum,pageSize);
                model.addAttribute("delList",slist);
                model.addAttribute("name","科学研究");
                break;
            case 3:
                PageInfo<Education> qlist = educationService.delList(pageNum,pageSize);
                model.addAttribute("delList",qlist);
                model.addAttribute("name","科普教育");
                break;
            case 4:
                PageInfo<Garden> glist = gardenService.delList(pageNum,pageSize);
                model.addAttribute("delList",glist);
                model.addAttribute("name","园林园艺");
                break;
            case 5:
                PageInfo<Question> list = questionservice.delList(pageNum,pageSize);
                model.addAttribute("delList",list);
                model.addAttribute("name","论坛文章");
        }
        model.addAttribute("contentType",contentType);

        return "/resumption";
    }

    @GetMapping("/dor")
    public String dor(@RequestParam("id") Long id,
                      @RequestParam("contenttype") Integer contenttype
                      ){
        StringBuilder builder = new StringBuilder("/resumption/");
        builder.append(contenttype);

        switch (contenttype) {
            case 1:
                newsService.resumptionbyId(id);
                break;
            case 2:
                scienceService.resumptionById(id);
                break;
            case 3:
                educationService.resumptionById(id);
                break;
            case 4:
                gardenService.resumptionById(id);
                break;
            case 5:
                questionservice.resumptionById(id);
                break;
        }
        return "redirect:"+builder.toString();
    }
}

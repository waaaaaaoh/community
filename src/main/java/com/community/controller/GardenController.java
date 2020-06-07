package com.community.controller;

import com.community.model.Garden;
import com.community.service.GardenService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class GardenController {
    @Autowired
    private GardenService gardenService;

    @GetMapping("/garden/{id}")
    public String gardenModel(Model model,
                              @PathVariable(name = "id") Integer type,
                              @RequestParam(defaultValue = "1") int pageNum,
                              @RequestParam(defaultValue = "6") int pageSize,
                              @RequestParam(value = "contentType",defaultValue = "0") Integer contentType) {
        String title = null;
        String titleEn = null;
        PageInfo<Garden> news;
        if(contentType == 0){
//          按分区查询
            news = gardenService.listByType(type, pageNum, pageSize);
        }else {
//            按分类查询  春夏秋冬
            news = gardenService.listByContenetype(contentType, pageNum, pageSize);
        }

        switch (type) {
            case 1:
                title = "四季览园";
                titleEn = "/ Four seasons garden";
                break;
            case 2:
                title = "专类园";
                titleEn = "/ specialized garden";
                break;
            case 3:
                title = "主题花展";
                titleEn = "/ Theme flower show";
                break;
            case 4:
                title = "观花指南";
                titleEn = "/ Guide";
                break;
            default:
        }
        model.addAttribute("news", news);
        model.addAttribute("type", type);
        model.addAttribute("title", title);
        model.addAttribute("titleEn",titleEn);
        return "garden";
    }
}

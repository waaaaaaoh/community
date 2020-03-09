package com.community.controller;

import com.community.model.News;
import com.community.service.NewsService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class NewsInformationController {

    @Autowired
    private NewsService newsService;
    @GetMapping("/news/{id}")
    public String news(Model model,
                       @PathVariable(name = "id") Integer type,
                       @RequestParam(defaultValue = "1") int pageNum,
                       @RequestParam(defaultValue = "6") int pageSize) {
        String title = null;
        PageInfo<News> news = newsService.listByType(type, pageNum, pageSize);
        switch (type) {
            case 1:
                title = "最新公告";
                break;
            case 2:
                title = "园内动态";
                break;
            case 3:
                title = "党政专区";
                break;
            default:
        }
        model.addAttribute("news", news);
        model.addAttribute("type", type);
        model.addAttribute("title", title);
        return "news";
    }

}

package com.community.controller;

import com.community.dto.QuestionDTO;
import com.community.mapper.QuestionMapper;
import com.community.model.*;
import com.community.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


import javax.servlet.http.HttpServletRequest;

@Controller
public class PublishController {


    @Autowired
    private Questionservice questionservice;

    @Autowired
    private NewsService newsService;

    @Autowired
    private ScienceService scienceService;

    @Autowired
    private EducationService educationService;

    @Autowired
    private GardenService gardenService;

    //    论坛文章发布
    @GetMapping("/publish/{id}")
    public String edit(@PathVariable(name = "id") Long id,
                       Model model) {
        QuestionDTO question = questionservice.getByid(id);
        model.addAttribute("title", question.getTitle());
        model.addAttribute("description", question.getDescription());
        model.addAttribute("tag", question.getTag());
        model.addAttribute("id", question.getId());
        return "publish";
    }

    @GetMapping("/publish")
    public String publish() {
        return "publish";
    }

    @PostMapping("/publish")
    public String doPublish(
            @RequestParam("title") String title,
            @RequestParam("description") String description,
            @RequestParam("tag") String tag,
            @RequestParam("id") Long id,
            HttpServletRequest request,
            Model model) {

        model.addAttribute("title", title);
        model.addAttribute("description", description);
        model.addAttribute("tag", tag);

        if (title == null || title == "") {
            model.addAttribute("error", "标题不能为空");
            return "publish";
        }
        if (description == null || description == "") {
            model.addAttribute("error", "问题补充不能为空");
            return "publish";
        }
        if (tag == null || tag == "") {
            model.addAttribute("error", "tag不能为空");
            return "publish";
        }

        User user = (User) request.getSession().getAttribute("user");
        if (user == null) {
            model.addAttribute("error", "用户未登录");
            return "publish";
        }

        Question question = new Question();
        question.setTitle(title);
        question.setDescription(description);
        question.setTag(tag);
        question.setCreator(user.getId());

        question.setAvatarUrl(user.getAvatarUrl());
        question.setId(id);

        questionservice.createOrUpdate(question);
        return "redirect:/";
    }


    //    园内文章发布
    @GetMapping("/article")
    public String article() {
        return "article";
    }

    @PostMapping("/article")
    public String doArticle(@RequestParam("contenttype") Integer contenttype,
                            @RequestParam("type") Integer type,
                            @RequestParam("title") String title,
                            @RequestParam("description") String description,
                            @RequestParam("introduction") String introduction,
                            HttpServletRequest request,
                            Model model) {
        System.out.println("contenttype:" + contenttype);
        System.out.println("type" + type);
        News news = null;
        Garden garden = null;
        Science science =null;
        if (contenttype != 4) {
            if(contenttype != 2){
                news = new News();
                news.setTitle(title);
                news.setContent(description);
                news.setGmtCreate(System.currentTimeMillis());
                news.setType(type);
                news.setCoverImg("http://www.cdszwy.com/uploadfile/2020/0301/20200301124546302.png");
            }else {
                science = new Science();
                science.setTitle(title);
                science.setContent(description);
                science.setGmtCreate(System.currentTimeMillis());
                science.setType(type);
                science.setDescription(introduction);
            }

        } else {
            garden = new Garden();
            if (type <= 4) {
                garden.setTitle(title);
                garden.setContent(description);
                garden.setGmtCreate(System.currentTimeMillis());
                garden.setType(type);
                garden.setCoverImg("http://www.cdszwy.com/uploadfile/2019/0326/20190326040431452.jpg");
            } else {
                garden.setTitle(title);
                garden.setContent(description);
                garden.setGmtCreate(System.currentTimeMillis());
                garden.setType(1);
                garden.setContentType(type - 4);
                garden.setCoverImg("http://www.cdszwy.com/uploadfile/2019/0326/20190326040431452.jpg");
            }
        }
        switch (contenttype) {
            case 1:
                newsService.create(news);
                break;
            case 2:
                scienceService.create(science);
                break;
            case 3:
                educationService.create(news);
                break;
            case 4:
                gardenService.create(garden);
                break;
            default:
        }

        return "article";
    }


}

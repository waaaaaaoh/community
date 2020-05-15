package com.community.controller;

import com.community.Provider.UCloudProvider;
import com.community.cache.TagCache;
import com.community.dto.FileDTO;
import com.community.dto.QuestionDTO;
import com.community.mapper.QuestionMapper;
import com.community.model.*;
import com.community.service.*;
import javafx.beans.binding.BooleanBinding;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;


import javax.servlet.http.HttpServletRequest;
import java.io.*;

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

    @Autowired
    private UCloudProvider uCloudProvider;

    //    论坛文章发布
    @GetMapping("/publish/{id}")
    public String edit(@PathVariable(name = "id") Long id,
                       Model model) {
        QuestionDTO question = questionservice.getByid(id);
        model.addAttribute("title", question.getTitle());
        model.addAttribute("description", question.getDescription());
        model.addAttribute("tag", question.getTag());
        model.addAttribute("id", question.getId());
        model.addAttribute("tags", TagCache.get());
        return "publish";
    }

    @GetMapping("/publish")
    public String publish(Model model) {
        model.addAttribute("tags", TagCache.get());
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
        model.addAttribute("tags", TagCache.get());

        if (title == null || title == "") {
            model.addAttribute("error", "标题不能为空");
            return "publish";
        }
        if (description == null || description == "") {
            model.addAttribute("error", "问题补充不能为空");
            return "publish";
        }
        if (tag == null || tag == "") {
            model.addAttribute("error", "标签不能为空");
            return "publish";
        }

        String invalid = TagCache.isValid(tag);
        if(invalid != null){
            model.addAttribute("error", "输入非法标签"+invalid);
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
                            @RequestParam("InputFile") MultipartFile file,
                            @RequestParam("description") String description,
                            @RequestParam("introduction") String introduction
                            ) {


        News news = null;
        Garden garden = null;
        Science science =null;
        String url = new String();

        if (!file.isEmpty()){
            try {
                url = uCloudProvider.upload(file.getInputStream(),file.getContentType(),file.getOriginalFilename());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        if (contenttype != 4) {
            if(contenttype != 2){
                news = new News();
                news.setTitle(title);
                news.setContent(description);
                news.setGmtCreate(System.currentTimeMillis());
                news.setType(type);
                news.setCoverImg(url);
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
                garden.setGmtCreate(System.currentTimeMillis());
                garden.setContent(description);
                garden.setType(type);
                garden.setCoverImg(url);
            } else {
                garden.setTitle(title);
                garden.setContent(description);
                garden.setGmtCreate(System.currentTimeMillis());
                garden.setType(1);
                garden.setContentType(type - 4);
                garden.setCoverImg(url);
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

        return "redirect:/home";
    }


}

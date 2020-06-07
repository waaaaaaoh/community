package com.community.controller;

import com.community.Provider.UCloudProvider;
import com.community.model.Garden;
import com.community.model.News;
import com.community.model.Science;
import com.community.service.EducationService;
import com.community.service.GardenService;
import com.community.service.NewsService;
import com.community.service.ScienceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Controller
public class UpdateController {

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

    @GetMapping("/update")
    public String update(@RequestParam("id") Long id,
                         @RequestParam("contenttype") Integer contenttype,
                         Model model){
        News news = null;
        Garden garden = null;
        Science science =null;
        switch (contenttype) {
            case 1:
                news = newsService.findbyId(id);
                break;
            case 2:
                science = scienceService.findById(id);
                break;
            case 3:
                news = educationService.findById(id);
                break;
            case 4:
                garden = gardenService.findById(id);
                break;
            default:
        }

        String title = null;
        String description = null;
        String content = null;
        String url = null;

        if (news != null){
            title = news.getTitle();
            content = news.getContent();
            url = news.getCoverImg();
        }else if(science != null){
            title = science.getTitle();
            description = science.getDescription();
            content = science.getContent();
        }else {
            title = garden.getTitle();
            content = garden.getContent();
            url = garden.getCoverImg();
        }

        model.addAttribute("title",title);
        model.addAttribute("description",description);
        model.addAttribute("content",content);
        model.addAttribute("id",id);
        model.addAttribute("contenttype",contenttype);
        model.addAttribute("url",url);
        return "update";
    }



    @PostMapping("/update")
    public String doUpdate(@RequestParam("contenttype") Integer contenttype,
                           @RequestParam("id") Long id,
                           @RequestParam("url")String coverImg,
                           @RequestParam("title") String title,
                           @RequestParam("InputFile") MultipartFile file,
                           @RequestParam("description") String description,
//                            简介
                           @RequestParam("introduction") String introduction
    ) {


        News news = null;
        Garden garden = null;
        Science science =null;
        String url = new String();

        if(!file.isEmpty()){
            try {
                url = uCloudProvider.upload(file.getInputStream(),file.getContentType(),file.getOriginalFilename());
//                System.out.println(url);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else if(contenttype!=2 && file.isEmpty()){
            url = coverImg;
        }


        if (contenttype != 4) {
            if(contenttype != 2){
                news = new News();
                news.setId(id);
                news.setTitle(title);
                news.setContent(description);
                news.setCoverImg(url);
            }else {
                science = new Science();
                science.setTitle(title);
                science.setId(id);
                science.setContent(description);
                science.setDescription(introduction);
            }

        } else {
            garden = new Garden();
            garden.setTitle(title);
            garden.setId(id);
            garden.setContent(description);
            garden.setCoverImg(url);

        }
        switch (contenttype) {
            case 1:
                newsService.update(news);
                break;
            case 2:
                scienceService.update(science);
                break;
            case 3:
                educationService.update(news);
                break;
            case 4:
                gardenService.update(garden);
                break;
            default:
        }

        return "redirect:/home";
    }
}

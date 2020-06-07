package com.community.controller;


import com.community.model.Question;
import com.community.service.Questionservice;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class IndexController {


    @Autowired
    private Questionservice questionservice;

    @GetMapping("/")
    public String index(Model model,
                        @RequestParam(defaultValue = "1") int pageNum,
                        @RequestParam(defaultValue = "5") int pageSize) {



        PageInfo<Question> question = questionservice.list(pageNum,pageSize);
//      原本的传往前端的值
//      List<QuestionDTO> questionList = questionservice.list(page,size);
        List<Question> top = questionservice.topList();
        model.addAttribute("question",question);
        model.addAttribute("top",top);

        return "index";

    }
}

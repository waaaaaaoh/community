package com.community.controller;

import com.community.dto.QuestionDTO;
import com.community.mapper.QuestionMapper;
import com.community.service.Questionservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class QuestionController {

    @Autowired
    private Questionservice questionservice;

    @GetMapping("/question/{id}")
    public String question(@PathVariable(name = "id")Long id,
                           Model model){
        QuestionDTO questionDTO = questionservice.getByid(id);
        //累加阅读数
        questionservice.incView(id);
        model.addAttribute("question",questionDTO);
        return "question";
    }
}

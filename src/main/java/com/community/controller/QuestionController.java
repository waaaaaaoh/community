package com.community.controller;

import com.community.dto.CommentDTO;
import com.community.dto.QuestionDTO;
import com.community.service.CommentService;
import com.community.service.Questionservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class QuestionController {

    @Autowired
    private Questionservice questionservice;

    @Autowired
    private CommentService commentService;

    @GetMapping("/question/{id}")
    public String question(@PathVariable(name = "id")Long id, Model model){
        QuestionDTO questionDTO = questionservice.getByid(id);
//      注意后期完善反转链表 实现新回复在页面上方
        List<CommentDTO> comments = commentService.ListByQuestionId(id);
        //累加阅读数
        questionservice.incView(id);
        model.addAttribute("question",questionDTO);
        model.addAttribute("comments",comments);
        return "question";
    }
}

package com.community.service;

import com.community.dto.QuestionDTO;
import com.community.mapper.QuestionMapper;
import com.community.mapper.UserMapper;
import com.community.model.Question;
import com.community.model.User;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class Questionservice {
    @Autowired
    private QuestionMapper questionMapper;
    @Autowired
    private UserMapper userMapper;

    public PageInfo<Question> list(int pageNum, int pageSize) {

        PageHelper.startPage(pageNum, pageSize);
        List<Question> questions = questionMapper.list();
//      原本的代码 为了避免二次查询影响pagehelper注释掉 直接在question表中加入头像字段
//      List<QuestionDTO> questionDTOList = new ArrayList<>();
//      for(Question question : questions){
//          User user = userMapper.findById(question.getCreator());
//          QuestionDTO questionDTO = new QuestionDTO();
//          BeanUtils.copyProperties(question,questionDTO);
//          questionDTO.setUser(user);
//          questionDTOList.add(questionDTO);
//        }
        PageInfo<Question> pageInfo = new PageInfo<>(questions);
        return pageInfo;
    }

    public PageInfo<Question> list(Integer userId, int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<Question> questions = questionMapper.listFindById(userId);
        PageInfo<Question> pageInfo = new PageInfo<>(questions);
        return pageInfo;
    }

    public QuestionDTO getByid(Integer id) {
        Question question = questionMapper.getByid(id);
        QuestionDTO questionDTO = new QuestionDTO();
        BeanUtils.copyProperties(question,questionDTO);
        User user = userMapper.findById(question.getCreator());
        questionDTO.setUser(user);
        return questionDTO;
    }

    public void createOrUpdate(Question question) {
        if(question.getId() == null){
            //创建
            question.setGmtCreate(System.currentTimeMillis());
            question.setGmtModified(question.getGmtCreate());
            questionMapper.create(question);
        }else{
            //更新
            question.setGmtModified(System.currentTimeMillis());
            questionMapper.update(question);
        }
    }
}

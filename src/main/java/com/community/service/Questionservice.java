package com.community.service;

import com.community.dto.QuestionDTO;
import com.community.exception.CustomizeErrorCode;
import com.community.exception.CustomizeException;
import com.community.mapper.QuestionMapper;
import com.community.mapper.UserMapper;
import com.community.model.Garden;
import com.community.model.Question;
import com.community.model.User;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;

@Service
public class Questionservice {
    @Autowired
    private QuestionMapper questionMapper;
    @Autowired
    private UserMapper userMapper;

    public PageInfo<Question> delList(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<Question> list = questionMapper.delList();
        PageInfo<Question> pageInfo = new PageInfo<>(list);
        return pageInfo;
    }


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

    public PageInfo<Question> list(Long userId, int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<Question> questions = questionMapper.listFindById(userId);
        PageInfo<Question> pageInfo = new PageInfo<>(questions);
        return pageInfo;
    }

    public QuestionDTO getByid(Long id) {
        Question question = questionMapper.getByid(id);
        if(question == null){
            throw new CustomizeException(CustomizeErrorCode.QUESTION_NOT_FOUND);
        }
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
            //更新 为解决权限问题 通过地址栏非法传入id可以修改他人的问题 后续应添加发布人与修改人验证功能
            question.setGmtModified(System.currentTimeMillis());
            int flag = questionMapper.update(question);
//            System.out.println(flag);
            if(flag != 1){
                throw new CustomizeException(CustomizeErrorCode.QUESTION_NOT_FOUND);
            }
        }
    }

    public void incView(Long id) {
        Question question = questionMapper.getByid(id);
//        question.setViewCount(question.getViewCount()+1);
        questionMapper.updateViews(question);
    }

    public void incCommentCount(Long id) {
        Question question = questionMapper.getByid(id);
        questionMapper.updateCommentCount(question);
    }

    public List<QuestionDTO> selectRelated(QuestionDTO questionDTO) {
            if(StringUtils.isBlank(questionDTO.getTag())){
                return new ArrayList<>();
            }
            QuestionDTO questionDTO1 = new QuestionDTO();
//          BeanUtils.copyProperties(source,target)
            BeanUtils.copyProperties(questionDTO,questionDTO1);
            questionDTO1.setTag(questionDTO1.getTag().replace(",","|"));
            List<QuestionDTO> questionDTOList = questionMapper.selectRelated(questionDTO1);
            return questionDTOList;

    }

    public void delById(Long id) {
        questionMapper.delById(id);
    }

    public List<Question> topList() {
        List<Question> questions = questionMapper.topList();
        return questions;
    }

    public void resumptionById(Long id) {
        questionMapper.resumptionById(id);
    }
}

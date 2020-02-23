package com.community.service;

import com.community.dto.CommentDTO;
import com.community.enums.CommentTypeEnum;
import com.community.exception.CustomizeErrorCode;
import com.community.exception.CustomizeException;
import com.community.mapper.CommentMapper;
import com.community.mapper.QuestionMapper;
import com.community.mapper.UserMapper;
import com.community.model.Comment;
import com.community.model.Question;
import com.community.model.User;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class CommentService {

    @Autowired
    private CommentMapper commentMapper;
    @Autowired
    private QuestionMapper questionMapper;
    @Autowired
    private UserMapper userMapper;

    @Transactional
    public void insert(Comment comment) {
        if(comment.getParentId() == null ||comment.getParentId() == 0){
            throw new CustomizeException(CustomizeErrorCode.TARGET_PARAM_NOT_FOUND);
        }

        if(comment.getType() == null || !CommentTypeEnum.isExist(comment.getType())){
            throw new CustomizeException((CustomizeErrorCode.TYPE_PARAM_NOT_FOUND));
        }

        if(comment.getType() == CommentTypeEnum.COMMENT.getType()){
            //回复评论
            Comment dbComment = commentMapper.selectByKey(comment.getParentId());
            if(dbComment == null){
                throw new CustomizeException((CustomizeErrorCode.COMMENT_NOT_FOUND));
            }
            commentMapper.insert(comment);
        }else {
            //回复问题
            Question question = questionMapper.getByid(comment.getParentId());
            if(question == null){
                throw new CustomizeException((CustomizeErrorCode.QUESTION_NOT_FOUND));
            }
            commentMapper.insert(comment);
            questionMapper.updateCommentCount(question);
        }
    }

    public List<CommentDTO> ListByQuestionId(Long id) {

        List<Comment> comments = commentMapper.selectListByKey(id);
        if(comments.size() == 0){
            return  new ArrayList<>();
        }
        //获取去重的评论人
        Set<Long> commentators = comments.stream().map(comment -> comment.getCommentator()).collect(Collectors.toSet());
        List<Long> userIds = new ArrayList<>();
        userIds.addAll(commentators);
//        获取评论人并转换为Map
        List<User> users =new ArrayList<>();
        for(Long userId : userIds){
            users.add(userMapper.findById(userId));
        }
        Map<Long,User> userMap = users.stream().collect(Collectors.toMap(user -> user.getId(),user -> user));
//        转换 comment 为 commentDTO
        List<CommentDTO> commentDTOS = comments.stream().map(comment -> {
            CommentDTO commentDTO = new CommentDTO();
            BeanUtils.copyProperties(comment,commentDTO);
            commentDTO.setUser(userMap.get(comment.getCommentator()));
            return  commentDTO;
        }).collect(Collectors.toList());
        return commentDTOS;
    }
}

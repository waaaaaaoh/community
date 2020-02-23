package com.community.mapper;

import com.community.model.Comment;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Service;

import java.util.List;

@Mapper
public interface CommentMapper {

    @Insert("insert into comment (id,parent_id,gmt_create,gmt_modified,type,commentator,like_count,content) values (#{id},#{parentId},#{gmtCreate},#{gmtModified},#{type},#{commentator},#{likeCount},#{content})")
    void insert(Comment comment);

    @Select("select * from comment where parent_id = #{parentId}")
    Comment selectByKey(Long parentId);

    @Select("select * from comment where parent_id = #{parentId}")
    List<Comment> selectListByKey(Long id);
}

package com.community.mapper;

import com.community.dto.QuestionDTO;
import com.community.model.Question;
import org.apache.ibatis.annotations.*;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Mapper
public interface QuestionMapper {
    @Insert("insert into question (title,description,gmt_create,gmt_modified,creator,tag,avatar_url) values (#{title},#{description},#{gmtCreate},#{gmtModified},#{creator},#{tag},#{avatarUrl})")
    void create(Question question);

    @Select("select * from question")
    List<Question> list();

    @Select("select * from question where creator = #{userId}")
    List<Question> listFindById(@Param("userId") Long userId);

    @Select("select * from question where id = #{id}")
    Question getByid(@Param("id") Long id);

    @Update("update question set title = #{title}, description = #{description}, gmt_modified = #{gmtModified}, tag = #{tag} where id = #{id}")
    int update(Question question);

    @Update("update question set view_count = view_count + 1 where id = #{id}")
    void updateViews(Question question);

    @Update("update question set comment_count = comment_count + 1 where id = #{id}")
    void updateCommentCount(Question question);

    @Select("select  * from question  where tag REGEXP #{tag} and id != #{id}")
    List<QuestionDTO> selectRelated(QuestionDTO questionDTO);
}

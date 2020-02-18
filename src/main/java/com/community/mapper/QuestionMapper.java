package com.community.mapper;

import com.community.model.Question;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface QuestionMapper {
    @Insert("insert into question (title,description,gmt_create,gmt_modified,creator,tag,avatar_url) values (#{title},#{description},#{gmtCreate},#{gmtModified},#{creator},#{tag},#{avatarUrl})")
    void create(Question question);

    @Select("select * from question")
    List<Question> list();

    @Select("select * from question where creator = #{userId}")
    List<Question> listFindById(@Param("userId") Integer userId);

    @Select("select * from question where id = #{id}")
    Question getByid(@Param("id") Integer id);

    @Update("update question set title = #{title}, description = #{description}, gmt_modified = #{gmtModified}, tag = #{tag} where id = #{id}")
    void update(Question question);
}

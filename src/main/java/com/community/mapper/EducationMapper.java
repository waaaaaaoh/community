package com.community.mapper;

import com.community.model.Education;
import com.community.model.News;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface EducationMapper {
    @Select("select * from education where type = #{type}")
    List<Education> listFindByType(@Param("type") int type);

    @Select("select * from education where id = #{id}")
    News findById(@Param("id") Long id);

    @Update("update education set view_count = view_count + 1 where id = #{id}")
    void updateView(@Param("id") Long id);

    @Insert("insert into education (title,cover_img,content,gmt_create,type) values (#{title},#{coverImg},#{content},#{gmtCreate},#{type})")
    void create(News news);

    @Select("select * from education  order by id desc limit 2;")
    List<Education> homepage();

    @Select("select * from education where id in ( select max(id) from news where id < #{id} and type = #{type})")
    News findpre(Long id, Integer type);

    @Select("select * from education where id in ( select min(id) from news where id > #{id} and type = #{type})")
    News findnext(Long id, Integer type);

    @Update("update education set title = #{title},cover_img = #{coverImg},content = #{content} where id = #{id}")
    void update(News news);
}

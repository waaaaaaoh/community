package com.community.mapper;

import com.community.model.News;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface NewsMapper {
    @Select("select * from news where type = #{type}")
    List<News> listFindByType(@Param("type") int type);

    @Select("select * from news where id = #{id}")
    News findById(@Param("id") Long id);

    @Update("update news set view_count = view_count + 1 where id = #{id}")
    void updateView(@Param("id") Long id);

    @Insert("insert into news (title,cover_img,content,gmt_create,type) values (#{title},#{coverImg},#{content},#{gmtCreate},#{type})")
    void create(News news);

    @Select("select * from news  order by id desc limit 6;")
    List<News> homepage();
}

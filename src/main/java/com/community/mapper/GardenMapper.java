package com.community.mapper;

import com.community.model.Garden;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface GardenMapper {
    @Select("select * from garden where type = #{type} and del != 1")
    List<Garden> listFindByType(@Param("type") Integer type);

    @Select("select * from garden where content_type = #{contentType} and del != 1")
    List<Garden> listFindByContenttype(@Param("contentType") Integer contentType);

    @Select("select * from garden where id = #{id} and del != 1")
    Garden findById(@Param("id") Long id);

    @Update("update garden set view_count = view_count + 1 where id = #{id}")
    void updateView(@Param("id") Long id);

    @Insert("insert into garden (title,cover_img,content,gmt_create,type,content_type) values (#{title},#{coverImg},#{content},#{gmtCreate},#{type},#{contentType})")
    void create(Garden garden);

    @Select("select * from garden where del != 1 order by id desc limit 8;")
    List<Garden> homepage();

    @Select("select * from garden where id in ( select max(id) from garden where id < #{id} and type = #{type} and del != 1)")
    Garden findpre(Long id, Integer type);

    @Select("select * from garden where id in ( select min(id) from garden where id > #{id} and type = #{type} and del != 1)")
    Garden findnext(Long id, Integer type);

    @Update("update garden set title = #{title},cover_img = #{coverImg},content = #{content} where id = #{id}")
    void update(Garden garden);

    @Update("update garden set del = 1 where id = #{id}")
    void delById(Long id);
}

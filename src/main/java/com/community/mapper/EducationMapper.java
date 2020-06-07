package com.community.mapper;

import com.community.model.Education;
import com.community.model.News;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface EducationMapper {
    @Select("select * from education where type = #{type} and del != 1 group by id desc")
    List<Education> listFindByType(@Param("type") int type);

    @Select("select * from education where id = #{id} and del != 1")
    News findById(@Param("id") Long id);

    @Update("update education set view_count = view_count + 1 where id = #{id}")
    void updateView(@Param("id") Long id);

    @Insert("insert into education (title,cover_img,content,gmt_create,type) values (#{title},#{coverImg},#{content},#{gmtCreate},#{type})")
    void create(News news);

    @Select("select * from education where del != 1 order by id desc limit 2;")
    List<Education> homepage();

    @Select("select * from education where id in ( select max(id) from education where id < #{id} and type = #{type} and del != 1)")
    News findpre(Long id, Integer type);

    @Select("select * from education where id in ( select min(id) from education where id > #{id} and type = #{type} and del != 1)")
    News findnext(Long id, Integer type);

    @Update("update education set title = #{title},cover_img = #{coverImg},content = #{content} where id = #{id}")
    void update(News news);

    @Update("update education set del = 1  where id = #{id}")
    void delById(Long id);

    @Select("select * from education where del =1")
    List<Education> delList();

    @Update("update education set del = 0  where id = #{id}")
    void resumptionById(Long id);
}

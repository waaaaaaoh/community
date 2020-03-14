package com.community.mapper;

import com.community.model.News;
import com.community.model.Science;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface ScienceMapper {
    @Select("select * from science where type = #{type}")
    List<Science> listFindByType(@Param("type") int type);

    @Select("select * from science where id = #{id}")
    News findById(@Param("id") Long id);

    @Update("update science set view_count = view_count + 1 where id = #{id}")
    void updateView(@Param("id") Long id);

    @Insert("insert into science (title,description,content,gmt_create,type) values (#{title},#{description},#{content},#{gmtCreate},#{type})")
    void create(Science science);
}

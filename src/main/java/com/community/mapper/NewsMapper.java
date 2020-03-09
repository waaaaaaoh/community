package com.community.mapper;

import com.community.model.News;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface NewsMapper {
    @Select("select * from news where type = #{type}")
    List<News> listFindByType(@Param("type") int type);
}

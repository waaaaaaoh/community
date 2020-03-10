package com.community.mapper;

import com.community.model.Garden;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface GardenMapper {
    @Select("select * from garden where type = #{type}")
    List<Garden> listFindByType(@Param("type") Integer type);

    @Select("select * from garden where content_type = #{contentType}")
    List<Garden> listFindByContenttype(@Param("contentType") Integer contentType);
}

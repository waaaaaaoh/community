package com.community.mapper;

import com.community.model.Education;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface EducationMapper {
    @Select("select * from education where type = #{type}")
    List<Education> listFindByType(@Param("type") int type);
}

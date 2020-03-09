package com.community.mapper;

import com.community.model.Science;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface ScienceMapper {
    @Select("select * from science where type = #{type}")
    List<Science> listFindByType(int type);
}

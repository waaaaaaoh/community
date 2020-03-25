package com.community.service;

import com.community.mapper.ScienceMapper;
import com.community.model.News;
import com.community.model.Science;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ScienceService {
    @Autowired
    private ScienceMapper scienceMapper;

    public PageInfo<Science> listByType(int type, int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<Science> list = scienceMapper.listFindByType(type);
        PageInfo<Science> pageInfo = new PageInfo<>(list);
        return pageInfo;
    }

    public void create(Science science) {
        scienceMapper.create(science);
    }

    public Science findById(Long id) {
        Science science = scienceMapper.findbyId(id);
        return science;
    }

    public void update(Science science) {
        scienceMapper.update(science);
    }
}

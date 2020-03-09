package com.community.service;

import com.community.mapper.NewsMapper;
import com.community.model.News;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NewsService {
    @Autowired
    private NewsMapper newsMapper;

    public PageInfo<News> listByType(int type, int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<News> list = newsMapper.listFindByType(type);
        PageInfo<News> pageInfo = new PageInfo<>(list);
        return pageInfo;
    }
}

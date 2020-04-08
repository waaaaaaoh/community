package com.community.service;

import com.community.mapper.EducationMapper;
import com.community.model.Education;
import com.community.model.News;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EducationService {

    @Autowired
    private EducationMapper educationMapper;


    public PageInfo<Education> listByType(Integer type, int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<Education> list = educationMapper.listFindByType(type);
        PageInfo<Education> pageInfo = new PageInfo<>(list);
        return pageInfo;
    }

    public void create(News news) {
        educationMapper.create(news);
    }

    public List<Education> homepage() {
        List<Education> educations = educationMapper.homepage();
        return  educations;
    }

    public News findById(Long id) {
        News news = educationMapper.findById(id);
        return news;
    }

    public void update(News news) {
        educationMapper.update(news);
    }

    public void delById(Long id) {
        educationMapper.delById(id);
    }
}

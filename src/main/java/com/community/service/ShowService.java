package com.community.service;

import com.community.mapper.EducationMapper;
import com.community.mapper.GardenMapper;
import com.community.mapper.NewsMapper;
import com.community.mapper.ScienceMapper;
import com.community.model.Garden;
import com.community.model.News;
import com.community.model.Show;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ShowService {
    @Autowired
    NewsMapper newsMapper;
    @Autowired
    ScienceMapper scienceMapper;
    @Autowired
    EducationMapper educationMapper;
    @Autowired
    GardenMapper gardenMapper;


    public Show findByContentTypeAndId(Long id, Integer contentType) {
        News news = null;
        Garden garden = null;
        Show show = new Show();
        switch (contentType) {
            case 1:
                news = newsMapper.findById(id);
                newsMapper.updateView(id);
                break;
            case 2:
                news = scienceMapper.findById(id);
                scienceMapper.updateView(id);
                break;
            case 3:
                news = educationMapper.findById(id);
                educationMapper.updateView(id);
                break;
            case 4:
                garden = gardenMapper.findById(id);
                gardenMapper.updateView(id);
                break;
            default:
        }
        if (garden == null) {
            show.setId(news.getId());
            show.setContent(news.getContent());
            show.setGmtCreate(news.getGmtCreate());
            show.setTitle(news.getTitle());
            show.setViewCount(news.getViewCount());
        } else {
            show.setId(garden.getId());
            show.setTitle(garden.getTitle());
            show.setContent(garden.getContent());
            show.setGmtCreate(garden.getGmtCreate());
            show.setViewCount(garden.getViewCount());
        }
        return show;
    }
}

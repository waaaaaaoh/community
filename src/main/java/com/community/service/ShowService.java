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
                if(news == null)
                    return null;
                newsMapper.updateView(id);
                break;
            case 2:
                news = scienceMapper.findById(id);
                if(news == null)
                    return null;
                scienceMapper.updateView(id);
                break;
            case 3:
                news = educationMapper.findById(id);
                if(news == null)
                    return null;
                educationMapper.updateView(id);
                break;
            case 4:
                garden = gardenMapper.findById(id);
                if (garden == null)
                    return null;
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
            show.setType(news.getType());
        } else {
            show.setId(garden.getId());
            show.setTitle(garden.getTitle());
            show.setContent(garden.getContent());
            show.setGmtCreate(garden.getGmtCreate());
            show.setViewCount(garden.getViewCount());
            show.setType(garden.getType());
        }
        return show;
    }

    public Show findpre(Long id, Integer contentType, Integer type) {
        News news = null;
        Garden garden = null;
        Show show = new Show();
        switch (contentType) {
            case 1:
                news = newsMapper.findpre(id,type);
                if(news == null)
                    return null;
                break;
            case 2:
                news = scienceMapper.findpre(id,type);
                if(news == null)
                    return null;
                break;
            case 3:
                news = educationMapper.findpre(id,type);
                if(news == null)
                    return null;
                break;
            case 4:
                garden = gardenMapper.findpre(id,type);
                if (garden == null)
                    return null;
                break;
            default:
        }
        if (garden == null) {
            show.setId(news.getId());
            show.setGmtCreate(news.getGmtCreate());
            show.setTitle(news.getTitle());
            show.setContent(news.getContent());
            show.setViewCount(news.getViewCount());
            show.setType(news.getType());
        } else {
            show.setId(garden.getId());
            show.setContent(garden.getContent());
            show.setTitle(garden.getTitle());
            show.setGmtCreate(garden.getGmtCreate());
            show.setViewCount(garden.getViewCount());
            show.setType(garden.getType());
        }
        return show;
    }

    public Show findnext(Long id, Integer contentType, Integer type) {
        News news = null;
        Garden garden = null;
        Show show = new Show();
        switch (contentType) {
            case 1:
                news = newsMapper.findnext(id,type);
                if(news == null)
                    return null;
                break;
            case 2:
                news = scienceMapper.findnext(id,type);
                if(news == null)
                    return null;
                break;
            case 3:
                news = educationMapper.findnext(id,type);
                if(news == null)
                    return null;
                break;
            case 4:
                garden = gardenMapper.findnext(id,type);
                if (garden == null)
                    return null;
                break;
            default:
        }
        if (garden == null) {
            show.setId(news.getId());
            show.setGmtCreate(news.getGmtCreate());
            show.setTitle(news.getTitle());
            show.setContent(news.getContent());
            show.setType(news.getType());
            show.setViewCount(news.getViewCount());
        } else {
            show.setId(garden.getId());
            show.setGmtCreate(garden.getGmtCreate());
            show.setContent(garden.getContent());
            show.setTitle(garden.getTitle());
            show.setViewCount(garden.getViewCount());
            show.setType(garden.getType());
        }
        return show;
    }
}

package com.community.service;

import com.community.mapper.GardenMapper;
import com.community.model.Garden;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GardenService {
    @Autowired
    private GardenMapper gardenMapper;

    public PageInfo<Garden> listByType(Integer type, int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<Garden> list = gardenMapper.listFindByType(type);
        PageInfo<Garden> pageInfo = new PageInfo<>(list);
        return pageInfo;
    }

    public PageInfo<Garden> listByContenetype(Integer contentType, int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<Garden> list = gardenMapper.listFindByContenttype(contentType);
        PageInfo<Garden> pageInfo = new PageInfo<>(list);
        return pageInfo;
    }

    public void create(Garden garden) {
        gardenMapper.create(garden);
    }

    public List<Garden> homepage() {
        List<Garden> gardens = gardenMapper.homepage();
        return  gardens;
    }

    public Garden findById(Long id) {
        Garden garden = gardenMapper.findById(id);
        return garden;
    }

    public void update(Garden garden) {
        gardenMapper.update(garden);
    }

    public void delById(Long id) {
        gardenMapper.delById(id);
    }

    public PageInfo<Garden> delList(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<Garden> list = gardenMapper.delList();
        PageInfo<Garden> pageInfo = new PageInfo<>(list);
        return pageInfo;
    }

    public void resumptionById(Long id) {
        gardenMapper.resumptionById(id);
    }
}

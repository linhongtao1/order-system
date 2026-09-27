package com.cqcj.service.impl;

import com.cqcj.bean.OrderInfo;
import com.cqcj.service.DishesService;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import com.cqcj.bean.Dishes;
import com.cqcj.mapper.DishesMapper;

import java.util.Date;
import java.util.List;

@Service
public class DishesServiceImpl implements DishesService {

    @Resource
    private DishesMapper dishesMapper;

    @Override
    public int deleteByPrimaryKey(Integer dishesId) {
        return dishesMapper.deleteByPrimaryKey(dishesId);
    }

    @Override
    public int insert(Dishes record) {
        return dishesMapper.insert(record);
    }

    @Override
    public int insertSelective(Dishes record) {
        return dishesMapper.insertSelective(record);
    }

    @Override
    public Dishes selectByPrimaryKey(Integer dishesId) {
        return dishesMapper.selectByPrimaryKey(dishesId);
    }

    @Override
    public int updateByPrimaryKeySelective(Dishes record) {
        return dishesMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(Dishes record) {
        return dishesMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public List<Dishes> findAll() {
        return dishesMapper.finAll();
    }

    @Override
    public List<Dishes> selectRecommend() {
        return dishesMapper.selectRecommend();
    }

    @Override
    public List<Dishes> selectByTime() {
        return dishesMapper.selectByTime();
    }

    @Override
    public List<Dishes> findDishes(String dishesName, Integer recommend) {
        return dishesMapper.findDishes(dishesName,recommend);
    }

    @Override
    public List<Dishes> findHot() {
        return dishesMapper.findHot();
    }

    @Override
    public List<Dishes> findCold() {
        return dishesMapper.findCold();
    }

    @Override
    public void addDishesCountById(Integer dishesId,Date date) {
        dishesMapper.addDishesCountById(dishesId,date);
    }


}

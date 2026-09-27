package com.cqcj.service;

import com.cqcj.bean.Dishes;

import java.util.Date;
import java.util.List;

public interface DishesService {
    int deleteByPrimaryKey(Integer dishesId);

    int insert(Dishes record);

    int insertSelective(Dishes record);

    Dishes selectByPrimaryKey(Integer dishesId);

    int updateByPrimaryKeySelective(Dishes record);

    int updateByPrimaryKey(Dishes record);

    List<Dishes> findAll();

    List<Dishes> selectRecommend();

    List<Dishes> selectByTime();

    List<Dishes> findDishes(String dishesName, Integer recommend);

    List<Dishes> findHot();

    List<Dishes> findCold();

    void addDishesCountById(Integer dishesId, Date date);
}

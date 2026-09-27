package com.cqcj.service;

import com.cqcj.bean.OrderDishes;
import com.cqcj.bean.OrderInfoAndDishes;

import java.util.List;

public interface OrderDishesService {
    int deleteByPrimaryKey(Integer orderId);

    int insert(OrderDishes record);

    int insertSelective(OrderDishes record);

    OrderDishes selectByPrimaryKey(Integer orderId);

    int updateByPrimaryKeySelective(OrderDishes record);

    int inserList(List<OrderDishes> orderDishesList);

    void deleteByOrderReference(String orderId);
}

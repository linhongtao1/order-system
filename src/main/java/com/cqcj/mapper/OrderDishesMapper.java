package com.cqcj.mapper;

import com.cqcj.bean.OrderDishes;
import com.cqcj.bean.OrderInfoAndDishes;

import java.util.List;

public interface OrderDishesMapper {
    int deleteByPrimaryKey(Integer orderId);

    int insert(OrderDishes record);

    int insertSelective(OrderDishes record);

    OrderDishes selectByPrimaryKey(Integer orderId);

    int updateByPrimaryKeySelective(OrderDishes record);

    int updateByPrimaryKey(OrderDishes record);

    int inserList(List<OrderDishes> orderDishesList);

    void deleteByOrderReference(String orderId);
}
package com.cqcj.service.impl;

import com.cqcj.bean.OrderInfoAndDishes;
import com.cqcj.service.OrderDishesService;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import com.cqcj.mapper.OrderDishesMapper;
import com.cqcj.bean.OrderDishes;

import java.util.List;

@Service
public class OrderDishesServiceImpl implements OrderDishesService {

    @Resource
    private OrderDishesMapper orderDishesMapper;

    @Override
    public int deleteByPrimaryKey(Integer orderId) {
        return orderDishesMapper.deleteByPrimaryKey(orderId);
    }

    @Override
    public int insert(OrderDishes record) {
        return orderDishesMapper.insert(record);
    }

    @Override
    public int insertSelective(OrderDishes record) {
        return orderDishesMapper.insertSelective(record);
    }

    @Override
    public OrderDishes selectByPrimaryKey(Integer orderId) {
        return orderDishesMapper.selectByPrimaryKey(orderId);
    }

    @Override
    public int updateByPrimaryKeySelective(OrderDishes record) {
        return orderDishesMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int inserList(List<OrderDishes> orderDishesList) {
        return orderDishesMapper.inserList(orderDishesList);
    }

    @Override
    public void deleteByOrderReference(String orderId) {
        orderDishesMapper.deleteByOrderReference(orderId);
    }

}

package com.cqcj.service.impl;

import com.cqcj.bean.DataInfo;
import com.cqcj.bean.OrderInfoAndDishes;
import com.cqcj.bean.ShoppingCart;
import com.cqcj.service.OrderInfoService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

import com.cqcj.mapper.OrderInfoMapper;
import com.cqcj.bean.OrderInfo;

import java.util.Date;
import java.util.List;

@Service
public class OrderInfoServiceImpl implements OrderInfoService {

    @Resource
    private OrderInfoMapper orderInfoMapper;

    @Override
    public int deleteByPrimaryKey(String orderId) {
        return orderInfoMapper.deleteByPrimaryKey(orderId);
    }

    @Override
    public int insert(OrderInfo record) {
        return orderInfoMapper.insert(record);
    }

    @Override
    public int insertSelective(OrderInfo record) {
        return orderInfoMapper.insertSelective(record);
    }

    @Override
    public OrderInfo selectByPrimaryKey(String orderId) {
        return orderInfoMapper.selectByPrimaryKey(orderId);
    }

    @Override
    public int updateByPrimaryKeySelective(OrderInfo record) {
        return orderInfoMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public double MonthMoney() {
        List<OrderInfo> orderInfoList = orderInfoMapper.findMonthMoney();
        double price = 0;
        if (orderInfoList != null) {
            for (OrderInfo orderInfo : orderInfoList) {
                price += orderInfo.getPrice();
            }
        }
        return price;
    }

    @Override
    public double DayMoney() {
        List<OrderInfo> orderInfoList = orderInfoMapper.findDayMoney();
        double price = 0;
        if (orderInfoList != null) {
            for (OrderInfo orderInfo : orderInfoList) {
                price += orderInfo.getPrice();
            }
        }
        return price;
    }

    @Override
    public List<DataInfo> Pie() {
        return orderInfoMapper.Pie();
    }

    @Override
    public List<OrderInfo> BarAndLine() {
        return orderInfoMapper.BarAndLine();
    }

    @Override
    public List<OrderInfoAndDishes> selectAllOrderInfo() {
        return orderInfoMapper.selectAllOrderInfo();
    }

    @Override
    public List<OrderInfoAndDishes> select(Integer waiterId, Integer tableId, Date orderBeginDate, Date orderEndDate) {
        return orderInfoMapper.select(waiterId,tableId,orderBeginDate,orderEndDate);
    }

    @Override
    public List<OrderInfoAndDishes> selectById(String orderId) {
        return orderInfoMapper.selectById(orderId);
    }

    @Override
    public int updateByPrimaryKey(OrderInfo record) {
        return orderInfoMapper.updateByPrimaryKey(record);
    }


}

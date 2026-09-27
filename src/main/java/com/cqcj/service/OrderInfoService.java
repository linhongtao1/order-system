package com.cqcj.service;

import com.cqcj.bean.DataInfo;
import com.cqcj.bean.OrderInfo;
import com.cqcj.bean.OrderInfoAndDishes;
import com.cqcj.bean.ShoppingCart;

import java.util.Date;
import java.util.List;

public interface OrderInfoService {
    int deleteByPrimaryKey(String orderId);

    int insert(OrderInfo record);

    int insertSelective(OrderInfo record);

    OrderInfo selectByPrimaryKey(String orderId);

    int updateByPrimaryKeySelective(OrderInfo record);

    double MonthMoney();

    int updateByPrimaryKey(OrderInfo record);

    double DayMoney();

    List<DataInfo> Pie();

    List<OrderInfo> BarAndLine();

    List<OrderInfoAndDishes> selectAllOrderInfo();

    List<OrderInfoAndDishes> select(Integer waiterId, Integer tableId, Date orderBeginDate, Date orderEndDate);

    List<OrderInfoAndDishes> selectById(String orderId);
}

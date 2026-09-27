package com.cqcj.controller;

import com.cqcj.bean.*;
import com.cqcj.service.DishesService;
import com.cqcj.service.OrderDishesService;
import com.cqcj.service.OrderInfoService;
import com.fasterxml.uuid.EthernetAddress;
import com.fasterxml.uuid.Generators;
import com.fasterxml.uuid.impl.TimeBasedGenerator;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.aspectj.weaver.ast.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

/**
 * @author a1165
 */
@RestController
@RequestMapping("/OrderInfo")
@Api(tags = "订单操作")
public class OrderInfoController {

    @Autowired
    OrderInfoService orderInfoService;
    @Autowired
    OrderDishesService orderDishesService;
    @Autowired
    DishesService dishesService;

    @ApiOperation("提交订单")
    @PostMapping()
    public Map<String, Object> payment(@RequestBody ShoppingCart shoppingCart) {
        Map<String, Object> map = new HashMap<String, Object>();
        TimeBasedGenerator generator = Generators.timeBasedGenerator(EthernetAddress.fromInterface());
        UUID uuid = generator.generate();
        String uuidToString = uuid.toString().replaceAll("-", "");
        Date date = new Date();
        long time = date.getTime();
        Date newDate = new Date();
        newDate.setTime(time + 1000*60*60);
        newDate.setTime(time + 1000*60*60);

        //数据封装
        OrderInfo orderInfo = new OrderInfo();
        orderInfo.setOrderId(uuidToString);
        orderInfo.setOrderState(1);
        orderInfo.setOrderBeginDate(date);
        orderInfo.setOrderEndDate(newDate);
        orderInfo.setTableId(shoppingCart.getTableId());
        orderInfo.setWaiterId(shoppingCart.getWaiterId());
        orderInfo.setPrice(shoppingCart.getTotalPrice());
        List<OrderDishes> orderDishesList = new ArrayList<OrderDishes>(shoppingCart.getCart().size());
        for (Dishes dishes : shoppingCart.getCart()) {
            OrderDishes orderDishes = new OrderDishes();
            orderDishes.setOrderReference(uuidToString);
            orderDishes.setDishes(dishes.getDishesId());
            dishesService.addDishesCountById(dishes.getDishesId(),date);
            orderDishes.setNum(1);
            orderDishesList.add(orderDishes);
        }
        //调用service
        int insert = orderInfoService.insert(orderInfo);
        int insert2 = orderDishesService.inserList(orderDishesList);
        map.put("message","success");
        return map;
    }

    @ApiOperation("查询订单信息")
    @GetMapping()
    public Map<String, Object> getOrderInfo(){
        Map<String, Object> map = new HashMap<String, Object>();
        List<OrderInfoAndDishes> orderInfoAndDishesList = orderInfoService.selectAllOrderInfo();
        List<OrderInfoAndDishes> OrderInfoAndDishesList2 = new ArrayList<OrderInfoAndDishes>();
        for (OrderInfoAndDishes orderInfoAndDishes : orderInfoAndDishesList) {
            Boolean flag = true;
            for (OrderInfoAndDishes orderInfoAndDishes2 : OrderInfoAndDishesList2) {
                if(orderInfoAndDishes.getOrderId().equals(orderInfoAndDishes2.getOrderId()) ){
                    flag = false;
                    orderInfoAndDishes2.setDishesName(orderInfoAndDishes2.getDishesName()+","+orderInfoAndDishes.getDishesName());
                }
            }
            if(flag){
                OrderInfoAndDishes orderInfoAndDishes1 = new OrderInfoAndDishes();
                orderInfoAndDishes1.setOrderId(orderInfoAndDishes.getOrderId());
                orderInfoAndDishes1.setOrderBeginDate(orderInfoAndDishes.getOrderBeginDate());
                orderInfoAndDishes1.setOrderEndDate(orderInfoAndDishes.getOrderEndDate());
                orderInfoAndDishes1.setWaiterId(orderInfoAndDishes.getWaiterId());
                orderInfoAndDishes1.setTableId(orderInfoAndDishes.getTableId());
                orderInfoAndDishes1.setPrice(orderInfoAndDishes.getPrice());
                orderInfoAndDishes1.setDishesId(orderInfoAndDishes.getDishesId());
                orderInfoAndDishes1.setDishesName(orderInfoAndDishes.getDishesName());
                orderInfoAndDishes1.setUserId(orderInfoAndDishes.getUserId());
                orderInfoAndDishes1.setUserName(orderInfoAndDishes.getUserName());
                OrderInfoAndDishesList2.add(orderInfoAndDishes1);
            }
        }
        map.put("orderInfoAndDishesList",OrderInfoAndDishesList2);
        return map;
    }

    @ApiOperation("订单条件查询")
    @PostMapping("/query")
    public Map<String, Object> select(@RequestBody OrderInfo orderInfo){
        Map<String, Object> map = new HashMap<String, Object>();
        Integer waiterId = orderInfo.getWaiterId();
        Integer tableId = orderInfo.getTableId();
        Date orderBeginDate = orderInfo.getOrderBeginDate();
        Date orderEndDate = orderInfo.getOrderEndDate();
        List<OrderInfoAndDishes> orderInfoAndDishesList = orderInfoService.select(waiterId,tableId,orderBeginDate,orderEndDate);
        List<OrderInfoAndDishes> OrderInfoAndDishesList2 = new ArrayList<OrderInfoAndDishes>();
        for (OrderInfoAndDishes orderInfoAndDishes : orderInfoAndDishesList) {
            Boolean flag = true;
            for (OrderInfoAndDishes orderInfoAndDishes2 : OrderInfoAndDishesList2) {
                if(orderInfoAndDishes.getOrderId().equals(orderInfoAndDishes2.getOrderId()) ){
                    flag = false;
                    orderInfoAndDishes2.setDishesName(orderInfoAndDishes2.getDishesName()+","+orderInfoAndDishes.getDishesName());
                }
            }
            if(flag){
                OrderInfoAndDishes orderInfoAndDishes1 = new OrderInfoAndDishes();
                orderInfoAndDishes1.setOrderId(orderInfoAndDishes.getOrderId());
                orderInfoAndDishes1.setOrderBeginDate(orderInfoAndDishes.getOrderBeginDate());
                orderInfoAndDishes1.setOrderEndDate(orderInfoAndDishes.getOrderEndDate());
                orderInfoAndDishes1.setWaiterId(orderInfoAndDishes.getWaiterId());
                orderInfoAndDishes1.setTableId(orderInfoAndDishes.getTableId());
                orderInfoAndDishes1.setPrice(orderInfoAndDishes.getPrice());
                orderInfoAndDishes1.setDishesId(orderInfoAndDishes.getDishesId());
                orderInfoAndDishes1.setDishesName(orderInfoAndDishes.getDishesName());
                orderInfoAndDishes1.setUserId(orderInfoAndDishes.getUserId());
                orderInfoAndDishes1.setUserName(orderInfoAndDishes.getUserName());
                OrderInfoAndDishesList2.add(orderInfoAndDishes1);
            }
        }
        map.put("orderInfoAndDishesList",OrderInfoAndDishesList2);
        return map;
    }

    @ApiOperation("订单删除")
    @DeleteMapping("/{orderId}")
    public Map<String, Object> delete(@PathVariable String orderId){
        Map<String, Object> map = new HashMap<String, Object>();
        orderInfoService.deleteByPrimaryKey(orderId);
        orderDishesService.deleteByOrderReference(orderId);
        return map;
    }

    @ApiOperation("根据订单id查询订单")
    @PostMapping("/{id}")
    public Map<String, Object> selectById(@PathVariable String id){
        Map<String, Object> map = new HashMap<String, Object>();
        List<OrderInfoAndDishes> orderInfoAndDishesList = orderInfoService.selectById(id);
        OrderInfoAndDishes result = new OrderInfoAndDishes();
        result.setOrderId(orderInfoAndDishesList.get(0).getOrderId());
        result.setOrderBeginDate(orderInfoAndDishesList.get(0).getOrderBeginDate());
        result.setOrderEndDate(orderInfoAndDishesList.get(0).getOrderEndDate());
        result.setWaiterId(orderInfoAndDishesList.get(0).getWaiterId());
        result.setTableId(orderInfoAndDishesList.get(0).getTableId());
        result.setDishesId(orderInfoAndDishesList.get(0).getDishesId());
        result.setUserId(orderInfoAndDishesList.get(0).getUserId());
        result.setUserName(orderInfoAndDishesList.get(0).getUserName());
        result.setPrice(0.0);
        Double price = 0.0;
        result.setDishesIdList(new ArrayList<Integer>());
        for (OrderInfoAndDishes orderInfoAndDishes : orderInfoAndDishesList) {
            price = result.getPrice();
            result.setPrice(price + orderInfoAndDishes.getPrice());
            result.setDishesName(result.getDishesName() + "," + orderInfoAndDishes.getDishesName());
            result.getDishesIdList().add(orderInfoAndDishes.getDishesId());
        }
        map.put("result",result);
        return map;
    }

    @ApiOperation("修改数据")
    @PutMapping()
    public Map<String, Object> update(@RequestBody ShoppingCart shoppingCart){
        Map<String, Object> map = new HashMap<String, Object>();
        OrderDishes orderDishes = new OrderDishes();
        OrderInfo orderInfo = new OrderInfo();
        orderInfo.setOrderId(shoppingCart.getOrderId());
        orderInfo.setPrice(shoppingCart.getTotalPrice());
        orderInfo.setWaiterId(shoppingCart.getWaiterId());
        orderInfo.setTableId(shoppingCart.getTableId());
        List<OrderDishes> orderDishesList = new ArrayList<>();
        for (Dishes dishes : shoppingCart.getCart()) {
            OrderDishes orderDishes1 = new OrderDishes();
            orderDishes1.setOrderReference(shoppingCart.getOrderId());
            orderDishes1.setNum(1);
            orderDishes1.setDishes(dishes.getDishesId());
            orderDishesList.add(orderDishes1);
        }
        orderInfoService.updateByPrimaryKeySelective(orderInfo);
        orderDishesService.deleteByOrderReference(shoppingCart.getOrderId());
        orderDishesService.inserList(orderDishesList);
        map.put("message","success");
        return map;
    }

}

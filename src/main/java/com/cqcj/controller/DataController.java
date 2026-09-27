package com.cqcj.controller;

import com.cqcj.bean.Dishes;
import com.cqcj.bean.DataInfo;
import com.cqcj.bean.OrderInfo;
import com.cqcj.service.DishesService;
import com.cqcj.service.OrderInfoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.criteria.Order;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author a1165
 */
@RestController
@RequestMapping("/Data")
@Api(tags = "数据操作")
public class DataController {
    @Autowired
    DishesService dishesService;
    @Autowired
    OrderInfoService orderInfoService;

    @ApiOperation("查询前五条热门菜品")
    @GetMapping("/Hot")
    public Map<String,Object> findHot(){
        Map<String,Object> map = new HashMap<String,Object>();
        List<Dishes> dishesList = dishesService.findHot();
        if(dishesList != null){
            map.put("dishesList",dishesList);
            map.put("message","success");
        }else{
            map.put("message","error");
        }
        return map;
    }

    @ApiOperation("查询最近未操作菜品")
    @GetMapping("/Cold")
    public Map<String,Object> findCold(){
        Map<String,Object> map = new HashMap<String,Object>();
        List<Dishes> dishesList = dishesService.findCold();
        if(dishesList != null){
            map.put("dishesList",dishesList);
            map.put("message","success");
        }else{
            map.put("message","error");
        }
        return map;
    }

    @ApiOperation("本月销售份额")
    @GetMapping("/MonthMoney")
    public Map<String,Object> MonthMoney(){
        Map<String,Object> map = new HashMap<String,Object>();
        double monthMoney = orderInfoService.MonthMoney();
        String  str = String.format("%.2f",monthMoney);
        monthMoney = Double.parseDouble(str);
        map.put("monthMoney",monthMoney);
        return map;
    }

    @ApiOperation("今日销售份额")
    @GetMapping("/DayMoney")
    public Map<String,Object> DayMoney(){
        Map<String,Object> map = new HashMap<String,Object>();
        double dayMoney = orderInfoService.DayMoney();
        String  str = String.format("%.2f",dayMoney);
        dayMoney = Double.parseDouble(str);
        map.put("dayMoney",dayMoney);
        return map;
    }

    @ApiOperation("员工销售情况饼图")
    @GetMapping("/Pie")
    public Map<String,Object> Pie(){
        Map<String,Object> map = new HashMap<String,Object>();
        List<DataInfo> pieInfoList = orderInfoService.Pie();
        List<Map> list = new ArrayList<Map>();
        for (DataInfo dataInfo : pieInfoList) {
            boolean flag = true;
            for (Map map1 : list) {
                //同一个员工
                if(map1.get("name").equals(dataInfo.getUserName())){
                    flag = false;
                    Double price = (Double) map1.get("value");
                    price += dataInfo.getPrice();
                    map1.put("value",price);
                }
            }
            if (flag){
                Map map2 = new HashMap(1);
                map2.put("name",dataInfo.getUserName());
                map2.put("value",dataInfo.getPrice());
                list.add(map2);
            }
        }
        map.put("data",list);
        return map;
    }

    @ApiOperation("季度数据")
    @GetMapping("/BarAndLine")
    public Map<String,Object> BarAndLine(){
        Map<String,Object> map = new HashMap<String,Object>();
        List<OrderInfo> orderInfoList = orderInfoService.BarAndLine();
        List<Double> barList = new ArrayList<>();
        List<Double> lineList = new ArrayList<>();
        Calendar calendar = Calendar.getInstance();
        int month = calendar.get(Calendar.MONTH) + 1;
        //当前月
        double dq = 0;
        //上个月
        double sg = 0;
        //上上个月
        double ssg = 0;
        for (OrderInfo orderInfo : orderInfoList) {
            if(orderInfo.getOrderBeginDate().getMonth() == month){
                dq += orderInfo.getPrice();
            }else if (orderInfo.getOrderBeginDate().getMonth() == month-1){
                sg += orderInfo.getPrice();
            }else {
                ssg += orderInfo.getPrice();
            }
        }
        barList.add(Double.parseDouble(String.format("%.2f",dq)));
        barList.add(Double.parseDouble(String.format("%.2f",ssg)));
        barList.add(Double.parseDouble(String.format("%.2f",sg)));
        lineList.add(Double.parseDouble(String.format("%.2f",dq)));
        lineList.add(Double.parseDouble(String.format("%.2f",ssg-dq)));
        lineList.add(Double.parseDouble(String.format("%.2f",sg-ssg)));

        map.put("barList",barList);
        map.put("lineList",lineList);
        return map;
    }
}

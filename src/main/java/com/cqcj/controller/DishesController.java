package com.cqcj.controller;

import com.cqcj.bean.Dishes;
import com.cqcj.bean.UserInfo;
import com.cqcj.service.DishesService;
import com.cqcj.util.OssUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author a1165
 */
@RestController
@RequestMapping("/Dishes")
@Api(tags = "菜品操作")
public class DishesController {

    @Autowired
    DishesService dishesService;
    OssUtil ossUtil = new OssUtil();

    @ApiOperation("查询所有")
    @GetMapping()
    public Map<String,Object> findAll(){
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("dishesList",dishesService.findAll());
        return map;
    }

    @ApiOperation("根据id删除")
    @DeleteMapping("/{id}")
    public Map<String,Object> deleteById(@PathVariable int id){
        Map<String,Object> map = new HashMap<String,Object>();
        int flag = dishesService.deleteByPrimaryKey(id);
        if(flag == 1){
            map.put("message","success");
        }else {
            map.put("message","error");
        }
        return map;
    }

    @ApiOperation("添加菜品")
    @PostMapping()
    public Map<String,Object> addDishes(@RequestBody Dishes dishes) throws ParseException, InterruptedException {
        Map<String,Object> map = new HashMap<String,Object>();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String dateString = simpleDateFormat.format(new Date());
        Date date = new Date(new SimpleDateFormat("yyyy-MM-dd").parse(dateString).getTime());
        dishes.setDishesDatetime(date);
        Thread.sleep(1000);
        dishes.setDishesImg(ossUtil.getURL());
        int insert = dishesService.insert(dishes);
        if(insert == 1){
            map.put("message","success");
        }else {
            map.put("message","error");
        }
        return map;
    }

    @ApiOperation("修改菜品信息")
    @PutMapping()
    public Map<String,Object> updateDishes(@RequestBody Dishes dishesInfo){
        Map<String,Object> map = new HashMap<String,Object>();
        int flag = dishesService.updateByPrimaryKey(dishesInfo);
        if(flag == 1){
            map.put("message","success");
        }else {
            map.put("message","error");
        }
        return map;
    }

    @ApiOperation("根据id查询菜品")
    @GetMapping("/{id}")
    public Map<String,Object> selectById(@PathVariable int id){
        Map<String,Object> map = new HashMap<String,Object>();
        Dishes dishes = dishesService.selectByPrimaryKey(id);
        if(dishes != null){
            map.put("dishes",dishes);
            map.put("message","success");
        }else {
            map.put("message","error");
        }
        return map;
    }

    @ApiOperation("查询所有特色菜")
    @GetMapping("/Recommend")
    public Map<String,Object> selectRecommend(){
        Map<String,Object> map = new HashMap<String,Object>();
        List<Dishes> dishesList = dishesService.selectRecommend();
        if(dishesList != null){
            map.put("dishes",dishesList);
            map.put("message","success");
        }else {
            map.put("message","error");
        }
        return map;
    }

    @ApiOperation("上传图片")
    @PostMapping("/upload")
    public void upload(MultipartFile photo){
        String url = ossUtil.uploadFile(photo);
        ossUtil.setURL(url);
        System.out.println(url);
    }

    @ApiOperation("根据时间降序查询最新菜品")
    @GetMapping("/Time")
    public Map<String,Object> selectByTime(){
        Map<String,Object> map = new HashMap<String,Object>();
        List<Dishes> dishesList = dishesService.selectByTime();
        if(dishesList != null){
            map.put("dishes",dishesList);
            map.put("message","success");
        }else {
            map.put("message","error");
        }
        return map;
    }

    @ApiOperation("条件查询")
    @PostMapping("/find")
    public Map<String, Object> findDishes(@RequestBody(required = false) Dishes dishes){
        List<Dishes> dishesList = dishesService.findDishes(dishes.getDishesName(),dishes.getRecommend());
        Map<String,Object> map = new HashMap<String,Object>();
        if(dishesList != null){
            map.put("dishesList",dishesList);
            map.put("message","success");
        }else{
            map.put("message","error");
        }
        return map;
    }
}

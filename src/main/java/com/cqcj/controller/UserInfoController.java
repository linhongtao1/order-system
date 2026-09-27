package com.cqcj.controller;

import com.cqcj.bean.UserInfo;
import com.cqcj.service.UserInfoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;


import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/userInfo")
@Api(tags = "用户操作")
public class UserInfoController {
    @Autowired
    UserInfoService userInfoService;

    @ApiOperation("添加用户")
    @PostMapping()
    public Map<String, Object> addUser(@RequestBody UserInfo userInfo){
        int insert = userInfoService.insert(userInfo);
        Map<String,Object> map = new HashMap<String,Object>();
        if(insert == 1){
            map.put("message","success");
        }else {
            map.put("message","error");
        }
        return map;
    }


    @ApiOperation("根据账号密码查询")
    @PostMapping("/login")
    public Map<String, Object> login(HttpServletResponse response, HttpSession session, String userAccount, String userPassword) throws IOException {
        UserInfo userInfo = userInfoService.selectByUserAccountAndUserPassword(userAccount,userPassword);
        Map<String,Object> map = new HashMap<String,Object>();
        if(userInfo==null){
            map.put("message","error");
            response.sendRedirect("/pages/index.html");
        }else {
            map.put("message","success");
            session.setAttribute("userInfo",userInfo);
            if(userInfo.getRole()==1){
                response.sendRedirect("/pages/admin/admin.html");
            }else if(userInfo.getRole() == 3){
                response.sendRedirect("/pages/user/user.html");
            }else{
                response.sendRedirect("/pages/waiter/waiter.html");
            }
        }
        return map;
    }

    @ApiOperation("查询所有")
    @GetMapping()
    public Map<String, Object> findAll(){
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("userInfoList",userInfoService.findAll());
        return map;
    }

    @ApiOperation("根据id进行删除")
    @DeleteMapping("/{id}")
    public Map<String, Object> deleteById(@PathVariable int id){
        int flag = userInfoService.deleteByPrimaryKey(id);
        Map<String,Object> map = new HashMap<String,Object>();
        if(flag == 1){
            map.put("message","success");
        }else {
            map.put("message","error");
        }
        return map;
    }

    @ApiOperation("修改用户信息")
    @PutMapping()
    public Map<String, Object> updateById(@RequestBody UserInfo userInfo){
        System.out.println(userInfo);
        int i = userInfoService.updateByPrimaryKeySelective(userInfo);
        Map<String,Object> map = new HashMap<String,Object>();;
        if(i == 1){
            map.put("message","success");
        }else {
            map.put("message","error");
        }
        return map;
    }

    @ApiOperation("根据id查询用户")
    @GetMapping("/{id}")
    public Map<String, Object> selectUserById(@PathVariable int id){
        UserInfo userInfo = userInfoService.selectByPrimaryKey(id);
        Map<String,Object> map = new HashMap<String,Object>();
        if(userInfo!=null){
            map.put("userInfo",userInfo);
            map.put("message","success");
        }else{
            map.put("message","error");
        }
        return map;
    }

    @ApiOperation("条件查询")
    @PostMapping("/find")
    public Map<String, Object> findUser(@RequestBody(required = false) UserInfo userInfo){
        List<UserInfo> userInfoList = userInfoService.findUser(userInfo.getUserAccount(),userInfo.getRole(),userInfo.getUserName());
        Map<String,Object> map = new HashMap<String,Object>();
        if(userInfoList != null){
            map.put("userInfoList",userInfoList);
            map.put("message","success");
        }else{
            map.put("message","error");
        }
        return map;
    }

    @ApiOperation("获取所有服务员信息")
    @GetMapping("/GetAllWaiter")
    public Map<String, Object> GetAllWaiter(){
        List<UserInfo> waiterList = userInfoService.GetAllWaiter();
        Map<String,Object> map = new HashMap<String,Object>();
        if(waiterList != null){
            map.put("waiterList",waiterList);
            map.put("message","success");
        }else{
            map.put("message","error");
        }
        return map;
    }
}

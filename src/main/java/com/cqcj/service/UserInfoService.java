package com.cqcj.service;

import com.cqcj.bean.UserInfo;

import java.util.List;

public interface UserInfoService {
    int updateByPrimaryKey(UserInfo record);

    int deleteByPrimaryKey(Integer userId);

    int insert(UserInfo record);

    int insertSelective(UserInfo record);

    UserInfo selectByPrimaryKey(Integer userId);

    int updateByPrimaryKeySelective(UserInfo record);

    UserInfo selectByUserAccountAndUserPassword(String userAccount, String userPassword);

    List<UserInfo> findAll();

    List<UserInfo> findUser(String userAccount, Integer role,String userName);

    List<UserInfo> GetAllWaiter();
}

package com.cqcj.mapper;

import com.cqcj.bean.UserInfo;

import java.util.List;

public interface UserInfoMapper {
    int deleteByPrimaryKey(Integer userId);

    int insert(UserInfo record);

    int insertSelective(UserInfo record);

    UserInfo selectByPrimaryKey(Integer userId);

    int updateByPrimaryKeySelective(UserInfo record);

    int updateByPrimaryKey(UserInfo record);

    UserInfo selectByUserAccountAndUserPassword(String userAccount, String userPassword);

    List<UserInfo> findAll();

    List<UserInfo> findUser(String userAccount, Integer role,String userName);

    List<UserInfo> GetAllWaiter();

    UserInfo selectByUserId(Integer userId);
}
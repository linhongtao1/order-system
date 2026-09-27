package com.cqcj.service.impl;

import com.cqcj.service.UserInfoService;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import com.cqcj.mapper.UserInfoMapper;
import com.cqcj.bean.UserInfo;

import java.util.List;

/**
 * @author a1165
 */
@Service
public class UserInfoServiceImpl implements UserInfoService {

    @Resource
    private UserInfoMapper userInfoMapper;

    @Override
    public int deleteByPrimaryKey(Integer userId) {
        return userInfoMapper.deleteByPrimaryKey(userId);
    }

    @Override
    public int insert(UserInfo record) {
        return userInfoMapper.insert(record);
    }

    @Override
    public int insertSelective(UserInfo record) {
        return userInfoMapper.insertSelective(record);
    }

    @Override
    public UserInfo selectByPrimaryKey(Integer userId) {
        return userInfoMapper.selectByUserId(userId);
    }

    @Override
    public int updateByPrimaryKeySelective(UserInfo record) {
        return userInfoMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public UserInfo selectByUserAccountAndUserPassword(String userAccount, String userPassword) {
        return userInfoMapper.selectByUserAccountAndUserPassword(userAccount,userPassword);
    }

    @Override
    public List<UserInfo> findAll() {
        return userInfoMapper.findAll();
    }

    @Override
    public List<UserInfo> findUser(String userAccount, Integer role,String userName) {
        return userInfoMapper.findUser(userAccount,role,userName);
    }

    @Override
    public List<UserInfo> GetAllWaiter() {
        return userInfoMapper.GetAllWaiter();
    }

    @Override
    public int updateByPrimaryKey(UserInfo record) {
        return userInfoMapper.updateByPrimaryKey(record);
    }

}

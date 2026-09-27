package com.cqcj.bean;

import lombok.Data;

@Data
public class UserInfo {
    private Integer userId;

    private String userAccount;

    private String userPassword;

    private Integer role;

    private String faceImg;

    private String userName;
}
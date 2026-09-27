package com.cqcj.bean;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author a1165
 */
@Data
public class DataInfo {
    private String userName;

    private Integer userId;

    private String userAccount;

    private Integer role;

    private String orderId;

    private Integer waiterId;

    private Double price;
}

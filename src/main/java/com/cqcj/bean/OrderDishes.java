package com.cqcj.bean;

import lombok.Data;

/**
 * @author a1165
 */
@Data
public class OrderDishes {
    /**
     * @param orderId 主键
     */
    private Integer orderId;

    /**
     * @param orderReference 订单外键
     */
    private String orderReference;

    /**
     * @param dishes 菜品外键
     */
    private Integer dishes;

    /**
     * @param num 菜品数量
     */
    private Integer num;

}
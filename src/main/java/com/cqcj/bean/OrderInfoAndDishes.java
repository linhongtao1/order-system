package com.cqcj.bean;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * @author a1165
 */
@Data
public class OrderInfoAndDishes {
    private String orderId;

    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date orderBeginDate;

    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date orderEndDate;

    private Integer waiterId;

    private Integer tableId;

    private Double price;

    private Integer dishesId;

    private String dishesName;

    private String userId;

    private String userName;

    private List<Integer> dishesIdList;
}

package com.cqcj.bean;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * @author a1165
 */
@Data
public class ShoppingCart {

    private List<Dishes> cart;

    private Double totalPrice;

    private Integer totalNum;

    private String orderId;

    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")
    private Date orderBeginDate;

    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")
    private Date orderEndDate;

    private Integer waiterId;

    private Integer tableId;

}

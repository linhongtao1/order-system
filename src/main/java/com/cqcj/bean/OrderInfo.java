package com.cqcj.bean;

import java.time.LocalDateTime;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

@Data
public class OrderInfo {
    private String orderId;

    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")
    private Date orderBeginDate;

    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")
    private Date orderEndDate;

    private Integer waiterId;

    private Integer orderState;

    private Integer tableId;

    private Double price;
}
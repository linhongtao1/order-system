package com.cqcj.bean;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

@Data
public class Dishes {
    private Integer dishesId;

    private String dishesName;

    private String dishesDescript;

    private String dishesTxt;

    private Integer recommend;

    private Double dishesPrice;

    private String dishesImg;

    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")
    private Date dishesDatetime;

    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")
    private Date dishesOperTime;

    private int dishesCount;
}
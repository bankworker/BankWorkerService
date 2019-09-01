package com.johnny.bankworker.vo;

import lombok.Data;

import java.util.List;

@Data
public class OrderVO extends BaseVO {
    private Integer orderID;
    private String bankCode;
    private String bankName;
    private String branchCode;
    private String branchName;
    private String orderDate;
    private String orderType;
    private String orderTypeText;
    private double originalAmount;
    private float discount;
    private double orderAmount;
    private String serviceDueDate;
    private String orderStatus;
    private String orderStatusText;
    private List<OrderDetailVO> orderDetailList;
}

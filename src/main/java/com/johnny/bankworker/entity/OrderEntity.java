package com.johnny.bankworker.entity;

import lombok.Data;

@Data
public class OrderEntity extends BaseEntity {
    private int orderID;
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
}

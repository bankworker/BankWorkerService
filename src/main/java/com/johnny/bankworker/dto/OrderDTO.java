package com.johnny.bankworker.dto;

import lombok.Data;

@Data
public class OrderDTO extends BaseDTO {
    private Integer orderID;
    private String bankCode;
    private String branchCode;
    private String orderDate;
    private String orderType;
    private double originalAmount;
    private float discount;
    private double orderAmount;
    private String serviceDueDate;
    private String orderStatus;
    private String orderItemList;
}

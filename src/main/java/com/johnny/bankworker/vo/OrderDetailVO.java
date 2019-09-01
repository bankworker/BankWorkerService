package com.johnny.bankworker.vo;

import lombok.Data;

@Data
public class OrderDetailVO extends BaseVO {
    private int orderDetailID;
    private int orderID;
    private int systemID;
    private String systemName;
    private double systemPrice;
}

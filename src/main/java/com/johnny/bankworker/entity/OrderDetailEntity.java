package com.johnny.bankworker.entity;

import lombok.Data;

@Data
public class OrderDetailEntity extends BaseEntity {
    private int orderDetailID;
    private int orderID;
    private int systemID;
    private String systemName;
    private double systemPrice;
}

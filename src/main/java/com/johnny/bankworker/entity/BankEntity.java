package com.johnny.bankworker.entity;

import lombok.Data;

@Data
public class BankEntity extends BaseEntity {
    private int bankID;
    private String bankName;
    private String bankCode;
    private String bankLogo;
}

package com.johnny.bankworker.entity;

import lombok.Data;

@Data
public class ClockInfoEntity extends BaseEntity {
    private int clockID;
    private String bankCode;
    private String branchCode;
    private int staffID;
    private String staffName;
    private String clockStatus;
    private String clockStatusText;
}

package com.johnny.bankworker.entity;

import lombok.Data;

@Data
public class ClockInfoEntity extends BaseEntity {
    private int clockID;
    private String bankCode;
    private String bankName;
    private String branchCode;
    private String branchName;
    private int staffID;
    private String staffName;
    private int staffPostID;
    private String staffPostName;
    private String staffPhoto;
    private String staffResume;
    private String clockStatus;
    private String clockStatusText;
}

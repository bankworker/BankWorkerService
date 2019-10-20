package com.johnny.bankworker.vo;

import lombok.Data;

@Data
public class ClockInfoVO extends BaseVO {
    private int clockID;
    private String bankCode;
    private String branchCode;
    private int staffID;
    private String staffName;
    private int staffPostID;
    private String staffPostName;
    private String staffPhoto;
    private String staffResume;
    private String clockStatus;
    private String clockStatusText;
}

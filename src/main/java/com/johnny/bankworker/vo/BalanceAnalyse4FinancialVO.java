package com.johnny.bankworker.vo;

import lombok.Data;

@Data
public class BalanceAnalyse4FinancialVO {
    private int financialID;
    private String financialName;
    private int processedBusinessCount;
    private String waitTotalTime;
    private String busyTotalTime;
    private String leaveTotalTime;
    private String offDutyTotalTime;
}

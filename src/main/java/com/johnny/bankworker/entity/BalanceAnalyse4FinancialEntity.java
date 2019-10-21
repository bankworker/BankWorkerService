package com.johnny.bankworker.entity;

import lombok.Data;

@Data
public class BalanceAnalyse4FinancialEntity {
    private int financialID;
    private String financialName;
    private int processedBusinessCount;
    private String waitTotalTime;
    private String busyTotalTime;
    private String leaveTotalTime;
    private String offDutyTotalTime;
}

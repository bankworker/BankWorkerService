package com.johnny.bankworker.entity;

import lombok.Data;

@Data
public class BalanceCallbackAnalyseEntity {
    private int callbackID;
    private String callbackMsg;
    private int processedCallbackCount;
}

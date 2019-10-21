package com.johnny.bankworker.vo;

import lombok.Data;

@Data
public class BalanceCallbackAnalyseVO {
    private int callbackID;
    private String callbackMsg;
    private int processedCallbackCount;
}

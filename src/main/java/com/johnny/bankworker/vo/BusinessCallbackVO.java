package com.johnny.bankworker.vo;

import lombok.Data;

@Data
public class BusinessCallbackVO extends BaseVO {
    private int callbackID;
    private String bankCode;
    private String branchCode;
    private String callbackMsg;
}

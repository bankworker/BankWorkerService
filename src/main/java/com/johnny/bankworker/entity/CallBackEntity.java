package com.johnny.bankworker.entity;

import lombok.Data;

@Data
public class CallBackEntity extends BaseEntity {
    private int callbackID;
    private String bankCode;
    private String branchCode;
    private String callbackMsg;
}

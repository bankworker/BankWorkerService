package com.johnny.bankworker.dto;

import lombok.Data;

@Data
public class BusinessCallbackDTO extends BaseDTO {
    private int callbackID;
    private String bankCode;
    private String branchCode;
    private String callbackMsg;
}

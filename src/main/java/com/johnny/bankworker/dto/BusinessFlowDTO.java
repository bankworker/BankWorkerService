package com.johnny.bankworker.dto;

import lombok.Data;

@Data
public class BusinessFlowDTO extends BaseDTO {
    private int businessID;
    private String bankCode;
    private String branchCode;
    private int senderID;
    private String sendTime;
    private int receiverID;
    private String receiveTime;
    private String businessStatus;
    private int callbackID;
    private String completeTime;
}

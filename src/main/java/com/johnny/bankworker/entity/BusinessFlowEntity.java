package com.johnny.bankworker.entity;

import lombok.Data;

@Data
public class BusinessFlowEntity extends BaseEntity {
    private int businessID;
    private String bankCode;
    private String branchCode;
    private int senderID;
    private String senderName;
    private String sendTime;
    private int receiverID;
    private String receiverName;
    private String receiveTime;
    private String businessStatus;
    private String businessStatusText;
    private int callbackID;
    private String otherCallbackMsg;
    private String completeTime;
}

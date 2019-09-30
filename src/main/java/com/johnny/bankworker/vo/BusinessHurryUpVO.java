package com.johnny.bankworker.vo;

import lombok.Data;

@Data
public class BusinessHurryUpVO extends BaseVO {
    private int hurryUpID;
    private String bankCode;
    private String branchCode;
    private int businessID;
    private int senderID;
    private String senderName;
    private int receiverID;
    private String receiverName;
}

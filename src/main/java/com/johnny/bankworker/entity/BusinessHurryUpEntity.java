package com.johnny.bankworker.entity;

import lombok.Data;

@Data
public class BusinessHurryUpEntity extends BaseEntity {
    private int hurryUpID;
    private String bankCode;
    private String branchCode;
    private int businessID;
    private int senderID;
    private String senderPhoto;
    private String senderName;
    private int receiverID;
    private String receiverName;
    private String hurryUpStatus;
}

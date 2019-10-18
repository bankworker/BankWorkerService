package com.johnny.bankworker.dto;

import lombok.Data;

@Data
public class BusinessHurryUpDTO extends BaseDTO {
    private int hurryUpID;
    private String bankCode;
    private String branchCode;
    private int businessID;
    private int senderID;
    private int receiverID;
    private String hurryUpStatus;
}

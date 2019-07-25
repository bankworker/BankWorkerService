package com.johnny.bankworker.dto;

import lombok.Data;

@Data
public class BankDTO extends BaseDTO {
    private Integer bankID;
    private String bankName;
    private String bankCode;
    private String bankLogo;
}

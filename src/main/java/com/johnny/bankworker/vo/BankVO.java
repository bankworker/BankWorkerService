package com.johnny.bankworker.vo;

import lombok.Data;

@Data
public class BankVO extends BaseVO {
    private Integer bankID;
    private String bankName;
    private String bankCode;
    private String bankLogo;
}

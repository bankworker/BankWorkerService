package com.johnny.bankworker.dto;

import lombok.Data;

@Data
public class BankBranchStaffAccountDTO extends BaseDTO {
    private Integer accountID;
    private String bankCode;
    private String branchCode;
    private Integer systemID;
    private String account;
    private String password;
}

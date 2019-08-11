package com.johnny.bankworker.dto;

import lombok.Data;

@Data
public class BankBranchStaffAccount4OriginalDTO extends BaseDTO {
    private Integer accountID;
    private String bankCode;
    private String branchCode;
    private String systemMultipleID;
    private String account;
    private String password;
}

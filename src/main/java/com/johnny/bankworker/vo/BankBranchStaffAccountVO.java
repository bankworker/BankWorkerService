package com.johnny.bankworker.vo;

import lombok.Data;

@Data
public class BankBranchStaffAccountVO extends BaseVO {
    private Integer accountID;
    private String bankCode;
    private String bankName;
    private String branchCode;
    private String branchName;
    private Integer systemID;
    private String systemName;
    private String account;
    private String password;
}

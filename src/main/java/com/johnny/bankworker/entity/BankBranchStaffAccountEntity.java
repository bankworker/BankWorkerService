package com.johnny.bankworker.entity;

import lombok.Data;

@Data
public class BankBranchStaffAccountEntity extends BaseEntity {
    private Integer accountID;
    private String bankCode;
    private String bankName;
    private String branchCode;
    private String branchName;
    private Integer systemID;
    private String systemName;
    private String accountOriginal;
    private String account;
    private int staffID;
    private String staffName;
    private int staffPostID;
    private String staffPostName;
    private String password;
    private String staffPhotoUrl;
}

package com.johnny.bankworker.entity;

import lombok.Data;

@Data
public class BankBranchStaffEntity extends BaseEntity {
    private int staffID;
    private String bankCode;
    private String bankName;
    private String branchCode;
    private String branchName;
    private String staffName;
    private String staffCellphone;
    private int staffPostID;
    private String staffPostName;
    private String staffPhotoUrl;
    private String staffResumeUrl;
}

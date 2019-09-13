package com.johnny.bankworker.vo;

import lombok.Data;

@Data
public class BankBranchStaffVO extends BaseVO {
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

package com.johnny.bankworker.dto;

import lombok.Data;

@Data
public class BankBranchStaffDTO extends BaseDTO {
    private int staffID;
    private String bankCode;
    private String branchCode;
    private String staffName;
    private String staffCellphone;
    private int staffPostID;
    private String staffPhotoUrl;
    private String staffResumeUrl;
}

package com.johnny.bankworker.vo;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class BankBranchStaffAccountVO extends BaseVO {
    private Integer accountID;
    private String bankCode;
    private String bankName;
    private String branchCode;
    private String branchName;
    private Integer systemID;
    private String systemName;
    private String account;
    private int staffID;
    private String staffName;
    private int staffPostID;
    private String staffPostName;
    private String password;
    private String staffPhotoUrl;
}

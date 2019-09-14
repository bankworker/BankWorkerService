package com.johnny.bankworker.vo;

import lombok.Data;

@Data
public class BranchStaffPostVO extends BaseVO {
    private int staffPostID;
    private String staffPostName;
    private String bankCode;
    private String branchCode;
}

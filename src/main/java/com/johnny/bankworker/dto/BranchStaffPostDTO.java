package com.johnny.bankworker.dto;

import lombok.Data;

@Data
public class BranchStaffPostDTO extends BaseDTO {
    private int staffPostID;
    private String staffPostName;
    private String bankCode;
    private String branchCode;
}

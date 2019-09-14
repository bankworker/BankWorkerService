package com.johnny.bankworker.entity;

import lombok.Data;

@Data
public class BranchStaffPostEntity extends BaseEntity {
    private int staffPostID;
    private String staffPostName;
    private String bankCode;
    private String branchCode;
}

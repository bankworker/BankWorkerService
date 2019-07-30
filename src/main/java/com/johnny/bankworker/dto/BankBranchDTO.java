package com.johnny.bankworker.dto;

import lombok.Data;

@Data
public class BankBranchDTO extends BaseDTO {
    private Integer branchID;
    private String bankCode;
    private String branchName;
    private String branchCode;
    private Integer provinceCode;
    private Integer cityCode;
    private Integer districtCode;
    private String address;
    private String branchLogo;
    private String branchContact;
    private String branchContactCellphone;
}

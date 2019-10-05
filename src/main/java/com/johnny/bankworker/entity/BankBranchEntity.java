package com.johnny.bankworker.entity;

import lombok.Data;

@Data
public class BankBranchEntity extends BaseEntity {
    private int branchID;
    private String bankCode;
    private String bankName;
    private String branchCode;
    private String branchName;
    private int provinceCode;
    private String provinceName;
    private int cityCode;
    private String cityName;
    private int districtCode;
    private String districtName;
    private String address;
    private String bankLogo;
    private String branchLogo;
    private String branchBackImage;
    private String branchContact;
    private String branchContactCellphone;
}

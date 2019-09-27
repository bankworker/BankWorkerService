package com.johnny.bankworker.entity;

import lombok.Data;

@Data
public class MediaModuleEntity extends BaseEntity {
    private int mediaModuleID;
    private String bankCode;
    private String branchCode;
    private String mediaModuleName;
    private String mediaModuleType;
    private String mediaModuleTypeText;
    private int mediaModuleOrder;
}

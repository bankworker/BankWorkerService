package com.johnny.bankworker.entity;

import lombok.Data;

@Data
public class MediaModuleDetailEntity extends BaseEntity {
    private int mediaDetailID;
    private String bankCode;
    private String branchCode;
    private int mediaModuleID;
    private String mediaDetailType;
    private String mediaDetailContent;
    private int mediaDetailOrder;
}

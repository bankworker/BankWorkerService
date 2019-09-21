package com.johnny.bankworker.entity;

import lombok.Data;

@Data
public class BranchArchiveDetailEntity extends BaseEntity {
    private int archiveDetailID;
    private String bankCode;
    private String branchCode;
    private int archiveID;
    private String archiveDetailType;
    private String archiveDetailContent;
    private int archiveDetailOrder;
}

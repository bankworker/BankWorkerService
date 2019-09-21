package com.johnny.bankworker.entity;

import lombok.Data;

@Data
public class BranchArchiveEntity extends BaseEntity {
    private int archiveID;
    private String bankCode;
    private String branchCode;
    private int archiveParentID;
    private String archiveName;
    private String archiveType;
    private int archiveOrder;
}

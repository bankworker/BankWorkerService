package com.johnny.bankworker.vo;

import lombok.Data;

@Data
public class BranchArchiveVO extends BaseVO {
    private int archiveID;
    private String bankCode;
    private String branchCode;
    private int archiveParentID;
    private String archiveName;
    private String archiveType;
    private int archiveOrder;
}

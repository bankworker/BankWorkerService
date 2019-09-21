package com.johnny.bankworker.dto;

import lombok.Data;

@Data
public class BranchArchiveDTO extends BaseDTO {
    private int archiveID;
    private int archiveSwapID;
    private String bankCode;
    private String branchCode;
    private int archiveParentID;
    private String archiveName;
    private String archiveType;
    private int archiveOrder;
}

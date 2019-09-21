package com.johnny.bankworker.vo;

import lombok.Data;

@Data
public class BranchArchiveDetailVO extends BaseVO {
    private int archiveDetailID;
    private String bankCode;
    private String branchCode;
    private int archivesID;
    private String archiveDetailType;
    private String archiveDetailContent;
    private int archiveDetailOrder;
}

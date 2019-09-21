package com.johnny.bankworker.dto;

import lombok.Data;

@Data
public class BranchArchiveDetailDTO extends BaseDTO {
    private int archiveDetailID;
    private int archiveDetailSwapID;
    private String bankCode;
    private String branchCode;
    private int archiveID;
    private String archiveDetailType;
    private String archiveDetailContent;
    private int archiveDetailOrder;
}

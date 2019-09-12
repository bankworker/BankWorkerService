package com.johnny.bankworker.dto;

import lombok.Data;

@Data
public class BranchNewsDTO extends BaseDTO {
    private int newsID;
    private String bankCode;
    private String branchCode;
    private String newsTitle;
    private String newsDate;
    private String thumbnailUrl;
    private String newsContent;
}
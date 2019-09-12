package com.johnny.bankworker.vo;

import lombok.Data;

@Data
public class BranchNewsVO extends BaseVO {
    private int newsID;
    private String bankCode;
    private String branchCode;
    private String newsTitle;
    private String newsDate;
    private String thumbnailUrl;
    private String newsContent;
}

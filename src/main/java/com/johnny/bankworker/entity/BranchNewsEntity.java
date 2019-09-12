package com.johnny.bankworker.entity;

import lombok.Data;

@Data
public class BranchNewsEntity extends BaseEntity {
    private int newsID;
    private String bankCode;
    private String branchCode;
    private String newsTitle;
    private String newsDate;
    private String thumbnailUrl;
    private String newsContent;
}

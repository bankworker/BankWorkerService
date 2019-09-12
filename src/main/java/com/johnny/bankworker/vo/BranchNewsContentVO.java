package com.johnny.bankworker.vo;

import lombok.Data;

@Data
public class BranchNewsContentVO extends BaseVO {
    private int newsContentID;
    private int newsID;
    private String newsContentType;
    private String newsContent;
}

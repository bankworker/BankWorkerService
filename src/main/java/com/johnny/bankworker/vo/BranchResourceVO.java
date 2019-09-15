package com.johnny.bankworker.vo;

import lombok.Data;

@Data
public class BranchResourceVO extends BaseVO {
    private int resourceID;
    private String bankCode;
    private String branchCode;
    private String resourceUrl;
}

package com.johnny.bankworker.dto;

import lombok.Data;

@Data
public class BranchResourceDTO extends BaseDTO {
    private int resourceID;
    private String bankCode;
    private String branchCode;
    private String resourceUrl;
    private String resourceUrlList;
}

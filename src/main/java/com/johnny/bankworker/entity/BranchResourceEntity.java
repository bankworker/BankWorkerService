package com.johnny.bankworker.entity;

import lombok.Data;

@Data
public class BranchResourceEntity extends BaseEntity {
    private int resourceID;
    private String bankCode;
    private String branchCode;
    private String resourceUrl;
}

package com.johnny.bankworker.dto;

import lombok.Data;

@Data
public class MediaModuleDTO extends BaseDTO {
    private int mediaModuleID;
    private int mediaModuleSwapID;
    private String bankCode;
    private String branchCode;
    private String mediaModuleName;
    private String mediaModuleType;
    private int mediaModuleOrder;
    private String detailJson;
}

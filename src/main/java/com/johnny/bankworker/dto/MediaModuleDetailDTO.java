package com.johnny.bankworker.dto;

import lombok.Data;

@Data
public class MediaModuleDetailDTO extends BaseDTO {
    private int mediaDetailID;
    private int mediaDetailSwapID;
    private String bankCode;
    private String branchCode;
    private int mediaModuleID;
    private String mediaDetailType;
    private int mediaDetailOrder;
}

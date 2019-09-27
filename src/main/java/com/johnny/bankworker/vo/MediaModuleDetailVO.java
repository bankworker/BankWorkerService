package com.johnny.bankworker.vo;

import lombok.Data;

@Data
public class MediaModuleDetailVO extends BaseVO {
    private int mediaDetailID;
    private int mediaModuleID;
    private String mediaDetailType;
    private String mediaDetailContent;
    private int mediaDetailOrder;
}

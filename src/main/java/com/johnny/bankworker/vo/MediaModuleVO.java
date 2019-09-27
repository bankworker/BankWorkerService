package com.johnny.bankworker.vo;

import lombok.Data;

import java.util.List;

@Data
public class MediaModuleVO extends BaseVO {
    private int mediaModuleID;
    private String bankCode;
    private String branchCode;
    private String mediaModuleName;
    private String mediaModuleType;
    private String mediaModuleTypeText;
    private int mediaModuleOrder;
    private List<MediaModuleDetailVO> mediaModuleDetailList;
}

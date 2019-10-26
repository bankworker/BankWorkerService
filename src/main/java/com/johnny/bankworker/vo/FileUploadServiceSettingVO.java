package com.johnny.bankworker.vo;

import lombok.Data;

@Data
public class FileUploadServiceSettingVO extends BaseVO {
    private int serviceID;
    private String bankCode;
    private String branchCode;
    private String serviceUrl;
}

package com.johnny.bankworker.dto;

import lombok.Data;

@Data
public class FileUploadServiceSettingDTO extends BaseDTO {
    private int serviceID;
    private String bankCode;
    private String branchCode;
    private String serviceUrl;
}

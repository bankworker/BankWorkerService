package com.johnny.bankworker.entity;

import lombok.Data;

@Data
public class FileUploadServiceSettingEntity extends BaseEntity {
    private int serviceID;
    private String bankCode;
    private String branchCode;
    private String serviceUrl;
}

package com.johnny.bankworker.vo;

import lombok.Data;

@Data
public class FileUploadUrlVO {
    private String bankCode;
    private String branchCode;
    private String serverFileUploadUrl;
    private String customerFileUploadUrl;
}

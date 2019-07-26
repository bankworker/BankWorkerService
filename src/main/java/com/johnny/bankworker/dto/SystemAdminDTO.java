package com.johnny.bankworker.dto;

import lombok.Data;

@Data
public class SystemAdminDTO extends BaseDTO {
    private Integer adminID;
    private String adminName;
    private String adminPhoto;
    private String adminCellphone;
    private String adminPassword;
}

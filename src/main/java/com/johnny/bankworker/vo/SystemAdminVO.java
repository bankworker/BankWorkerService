package com.johnny.bankworker.vo;

import lombok.Data;

@Data
public class SystemAdminVO extends BaseVO {
    private Integer adminID;
    private String adminName;
    private String adminPhoto;
    private String adminCellphone;
    private String adminPassword;
}

package com.johnny.bankworker.entity;

import lombok.Data;

@Data
public class SystemAdminEntity extends BaseEntity {
    private Integer adminID;
    private String adminName;
    private String adminPhoto;
    private String adminCellphone;
    private String adminPassword;
}

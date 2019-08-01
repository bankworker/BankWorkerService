package com.johnny.bankworker.entity;

import lombok.Data;

@Data
public class SystemSettingEntity extends BaseEntity {
    private int systemID;
    private String systemName;
    private double systemPrice;
    private double servicePrice;
}

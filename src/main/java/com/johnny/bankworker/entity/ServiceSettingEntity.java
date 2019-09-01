package com.johnny.bankworker.entity;

import lombok.Data;

@Data
public class ServiceSettingEntity extends BaseEntity {
    private int serviceID;
    private int serviceYear;
    private double servicePrice;
}

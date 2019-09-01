package com.johnny.bankworker.vo;

import lombok.Data;

@Data
public class ServiceSettingVO extends BaseVO {
    private int serviceID;
    private int serviceYear;
    private double servicePrice;
}

package com.johnny.bankworker.vo;

import lombok.Data;

@Data
public class SystemSettingVO extends BaseVO {
    private int systemID;
    private String systemName;
    private double systemPrice;
    private double servicePrice;
}

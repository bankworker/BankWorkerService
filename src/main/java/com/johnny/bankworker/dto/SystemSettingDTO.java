package com.johnny.bankworker.dto;

import lombok.Data;

@Data
public class SystemSettingDTO extends BaseDTO {
    private int systemID;
    private String systemName;
    private double systemPrice;
    private double servicePrice;
}

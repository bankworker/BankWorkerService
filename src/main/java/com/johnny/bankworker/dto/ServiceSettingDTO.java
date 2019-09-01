package com.johnny.bankworker.dto;

import lombok.Data;

@Data
public class ServiceSettingDTO extends BaseDTO {
    private int serviceID;
    private int serviceYear;
    private double servicePrice;
}

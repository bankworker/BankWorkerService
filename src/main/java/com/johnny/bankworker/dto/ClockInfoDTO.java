package com.johnny.bankworker.dto;

import com.johnny.bankworker.dto.BaseDTO;
import lombok.Data;

@Data
public class ClockInfoDTO extends BaseDTO {
    private int clockID;
    private String bankCode;
    private String branchCode;
    private int staffID;
    private String clockStatus;
}

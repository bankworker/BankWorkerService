package com.johnny.bankworker.service.workerbalance;

import com.johnny.bankworker.dto.ClockInfoDTO;
import com.johnny.bankworker.service.BaseService;
import com.johnny.bankworker.vo.UnifiedResponse;

public interface ClockInfoService extends BaseService<ClockInfoDTO> {
    UnifiedResponse findCurrentClockInfo(String bankCode, String branchCode, int staffID);

    UnifiedResponse findClockedFinancialList(String bankCode, String branchCode);
}

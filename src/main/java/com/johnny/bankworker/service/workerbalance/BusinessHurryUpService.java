package com.johnny.bankworker.service.workerbalance;

import com.johnny.bankworker.dto.BusinessHurryUpDTO;
import com.johnny.bankworker.service.BaseService;
import com.johnny.bankworker.vo.UnifiedResponse;

public interface BusinessHurryUpService extends BaseService<BusinessHurryUpDTO> {
    UnifiedResponse findLatestHurryUp4Receiver(String bankCode, String branchCode, int receiverID);
}

package com.johnny.bankworker.service;

import com.johnny.bankworker.dto.BusinessCallbackDTO;
import com.johnny.bankworker.vo.UnifiedResponse;

public interface BusinessCallbackService extends BaseService<BusinessCallbackDTO> {
    UnifiedResponse findList4Branch(int pageNumber, int pageSize, String bankCode, String branchCode);

    UnifiedResponse existCheck(String bankCode, String branchCode, String callbackMessage);
}

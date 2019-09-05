package com.johnny.bankworker.service;

import com.johnny.bankworker.dto.BankBranchStaffAccount4OriginalDTO;
import com.johnny.bankworker.vo.UnifiedResponse;

public interface BankBranchStaffAccountService extends BaseService<BankBranchStaffAccount4OriginalDTO> {
    UnifiedResponse login(String account, String password, int systemID);

    UnifiedResponse findAuthorizedSystem(int accountID);
}

package com.johnny.bankworker.service;

import com.johnny.bankworker.dto.BankBranchStaffAccount4OriginalDTO;
import com.johnny.bankworker.dto.BankBranchStaffAccountDTO;
import com.johnny.bankworker.vo.UnifiedResponse;

public interface BankBranchStaffAccountService extends BaseService<BankBranchStaffAccount4OriginalDTO> {
    UnifiedResponse login(String account, String password, int systemID);

    UnifiedResponse loginBalance(String bankCode, String branchCode, int postID, String cellphone, String password);

    UnifiedResponse findAuthorizedSystem(int accountID);

    UnifiedResponse changePassword(BankBranchStaffAccountDTO dto);
}

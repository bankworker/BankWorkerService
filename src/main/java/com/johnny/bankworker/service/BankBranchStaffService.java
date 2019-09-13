package com.johnny.bankworker.service;

import com.johnny.bankworker.dto.BankBranchStaffDTO;
import com.johnny.bankworker.vo.UnifiedResponse;

public interface BankBranchStaffService extends BaseService<BankBranchStaffDTO> {
    UnifiedResponse findList4Branch(int pageNumber, int pageSize, String bankCode, String branchCode);
}

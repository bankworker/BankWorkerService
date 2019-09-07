package com.johnny.bankworker.service;

import com.johnny.bankworker.dto.BankBranchDTO;
import com.johnny.bankworker.entity.BankBranchEntity;
import com.johnny.bankworker.vo.BankBranchVO;
import com.johnny.bankworker.vo.UnifiedResponse;

public interface BankBranchService extends BaseService<BankBranchDTO> {
    UnifiedResponse findListByBankCode(String bankCode);

    UnifiedResponse findListByCode(String bankCode, String branchCode);

    UnifiedResponse changeLogo(BankBranchDTO dto);
}

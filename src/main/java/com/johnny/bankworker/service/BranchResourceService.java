package com.johnny.bankworker.service;

import com.johnny.bankworker.dto.BranchResourceDTO;
import com.johnny.bankworker.vo.UnifiedResponse;

public interface BranchResourceService extends BaseService<BranchResourceDTO> {
    UnifiedResponse findList4Branch(int pageNumber, int pageSize, String bankCode, String branchCode);
}

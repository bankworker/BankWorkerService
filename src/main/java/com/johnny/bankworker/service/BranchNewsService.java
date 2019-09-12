package com.johnny.bankworker.service;

import com.johnny.bankworker.dto.BranchNewsDTO;
import com.johnny.bankworker.vo.UnifiedResponse;

public interface BranchNewsService extends BaseService<BranchNewsDTO> {
    UnifiedResponse findList4Branch(int pageNumber, int pageSize, String bankCode, String branchCode);

    UnifiedResponse find(int newsID);
}

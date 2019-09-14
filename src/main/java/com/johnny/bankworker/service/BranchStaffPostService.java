package com.johnny.bankworker.service;

import com.johnny.bankworker.dto.BranchStaffPostDTO;
import com.johnny.bankworker.vo.UnifiedResponse;

public interface BranchStaffPostService extends BaseService<BranchStaffPostDTO> {
    UnifiedResponse findList(int pageNumber, int pageSize, String bankCode, String branchCode);

    UnifiedResponse existCheck(String bankCode, String branchCode, String staffPostName);

    UnifiedResponse checkStaffPostIsUsing(String bankCode, String branchCode, int staffPostID);
}

package com.johnny.bankworker.service;

import com.johnny.bankworker.dto.MediaModuleDTO;
import com.johnny.bankworker.dto.MediaModuleDetailDTO;
import com.johnny.bankworker.vo.UnifiedResponse;

public interface BranchMediaModuleService extends BaseService<MediaModuleDTO> {
    UnifiedResponse findList4Branch(int pageNumber, int pageSize, String bankCode, String branchCode);

    UnifiedResponse find(String bankCode, String branchCode, int mediaModuleID);

    UnifiedResponse changeMediaModuleOrder(MediaModuleDTO dto);

    UnifiedResponse changeMediaModuleDetailOrder(MediaModuleDetailDTO dto);

    UnifiedResponse deleteMediaModule(String bankCode, String branchCode, int mediaModuleID);

    UnifiedResponse deleteMediaModuleDetail(String bankCode, String branchCode, int mediaModuleID, int mediaDetailID);
}

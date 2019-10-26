package com.johnny.bankworker.service;

import com.johnny.bankworker.dto.FileUploadServiceSettingDTO;
import com.johnny.bankworker.vo.FileUploadServiceSettingVO;
import com.johnny.bankworker.vo.UnifiedResponse;

public interface FileUploadSettingService extends BaseService<FileUploadServiceSettingDTO> {
    UnifiedResponse findList4Branch(int pageNumber, int pageSize, String bankCode, String branchCode);
}

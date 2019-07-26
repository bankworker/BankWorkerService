package com.johnny.bankworker.service;

import com.johnny.bankworker.dto.SystemAdminDTO;
import com.johnny.bankworker.entity.SystemAdminEntity;
import com.johnny.bankworker.vo.SystemAdminVO;
import com.johnny.bankworker.vo.UnifiedResponse;

public interface SystemAdminService extends BaseService<SystemAdminDTO, SystemAdminVO, SystemAdminEntity> {

    UnifiedResponse login(String cellphone, String password);

    UnifiedResponse changePassword(SystemAdminDTO dto);
}

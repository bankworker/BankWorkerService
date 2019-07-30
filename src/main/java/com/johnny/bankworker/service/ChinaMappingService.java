package com.johnny.bankworker.service;

import com.johnny.bankworker.vo.UnifiedResponse;

public interface ChinaMappingService {
    UnifiedResponse searchByParentCode(int parentCode);
}

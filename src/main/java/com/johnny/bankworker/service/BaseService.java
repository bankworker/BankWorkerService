package com.johnny.bankworker.service;

import com.johnny.bankworker.vo.UnifiedResponse;

public interface BaseService<T> {
    UnifiedResponse findList(int pageNumber, int pageSize, String dataStatus);

    UnifiedResponse find(int id, String dataStatus);

    UnifiedResponse existCheck(String value);

    UnifiedResponse add(T dto);

    UnifiedResponse change(T dto);

    UnifiedResponse changeDataStatus(T dto);

    UnifiedResponse delete(int id);
}

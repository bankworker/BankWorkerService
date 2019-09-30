package com.johnny.bankworker.mapper;

import com.johnny.bankworker.entity.BusinessCallbackEntity;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface BusinessCallbackMapper extends BaseMapper<BusinessCallbackEntity> {
    int existCheck4Branch(String bankCode, String branchCode, String callbackMessage);
}

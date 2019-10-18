package com.johnny.bankworker.mapper.workerbalance;

import com.johnny.bankworker.entity.CallBackEntity;
import com.johnny.bankworker.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface BusinessCallbackMapper extends BaseMapper<CallBackEntity> {
    int existCheck4Branch(String bankCode, String branchCode, String callbackMessage);
}

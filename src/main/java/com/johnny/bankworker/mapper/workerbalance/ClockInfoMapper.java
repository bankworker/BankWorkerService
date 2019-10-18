package com.johnny.bankworker.mapper.workerbalance;

import com.johnny.bankworker.entity.ClockInfoEntity;
import com.johnny.bankworker.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ClockInfoMapper extends BaseMapper<ClockInfoEntity> {
    ClockInfoEntity searchCurrentClockInfo(String bankCode, String branchCode, int staffID);
}

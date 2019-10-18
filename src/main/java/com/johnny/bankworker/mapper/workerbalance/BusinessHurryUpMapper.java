package com.johnny.bankworker.mapper.workerbalance;

import com.johnny.bankworker.entity.BusinessHurryUpEntity;
import com.johnny.bankworker.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface BusinessHurryUpMapper extends BaseMapper<BusinessHurryUpEntity> {
    BusinessHurryUpEntity searchLatestHurryUp4Receiver(String bankCode, String branchCode, int receiverID);
}

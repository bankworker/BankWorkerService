package com.johnny.bankworker.mapper.workerbalance;

import com.johnny.bankworker.entity.BusinessFlowEntity;
import com.johnny.bankworker.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BusinessFlowMapper extends BaseMapper<BusinessFlowEntity> {

    List<BusinessFlowEntity> searchReceiveList(String bankCode, String branchCode, int receiverID);

    BusinessFlowEntity searchNewReceiveInfo(String bankCode, String branchCode, int receiverID);

    List<BusinessFlowEntity> searchSendList(String bankCode, String branchCode, int senderID);

    int updateBusinessStatus(BusinessFlowEntity entity);

    int completeBusiness(BusinessFlowEntity entity);

    int updateCallBack(BusinessFlowEntity entity);
}

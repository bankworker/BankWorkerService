package com.johnny.bankworker.service.workerbalance;

import com.johnny.bankworker.dto.BusinessFlowDTO;
import com.johnny.bankworker.service.BaseService;
import com.johnny.bankworker.vo.UnifiedResponse;

public interface BusinessFlowService extends BaseService<BusinessFlowDTO> {
    UnifiedResponse findReceiveList(String bankCode, String branchCode, int receiverID);

    UnifiedResponse findNewReceiveInfo(String bankCode, String branchCode, int receiverID);

    UnifiedResponse findSendList(String bankCode, String branchCode, int senderID);

    UnifiedResponse changeBusinessStatus(BusinessFlowDTO dto);

    UnifiedResponse completeBusiness(BusinessFlowDTO dto);

    UnifiedResponse addCallBack(BusinessFlowDTO dto);
}

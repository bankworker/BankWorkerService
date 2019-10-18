package com.johnny.bankworker.vo;

import com.johnny.bankworker.vo.BaseVO;
import lombok.Data;

@Data
public class BusinessFlowVO extends BaseVO {
    private int businessID;
    private String bankCode;
    private String branchCode;
    private int senderID;
    private String senderName;
    private String senderPhoto;
    private String sendTime;
    private int receiverID;
    private String receiverName;
    private String receiveTime;
    private String businessStatus;
    private String businessStatusText;
    private int callbackID;
    private String completeTime;
}

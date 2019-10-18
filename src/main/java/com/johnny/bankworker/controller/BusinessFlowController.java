package com.johnny.bankworker.controller;

import com.johnny.bankworker.dto.BusinessFlowDTO;
import com.johnny.bankworker.service.workerbalance.BusinessFlowService;
import com.johnny.bankworker.vo.UnifiedResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/balance/businessFlow")
public class BusinessFlowController {
    @Autowired
    private BusinessFlowService serviceImpl;

    @RequestMapping(value = "/receive/{bankCode}/{branchCode}/{receiverID}", method = RequestMethod.GET)
    public UnifiedResponse findReceiveList(@PathVariable("bankCode") String bankCode,
                               @PathVariable("branchCode") String branchCode,
                               @PathVariable("receiverID") int receiverID){
        return serviceImpl.findReceiveList(bankCode, branchCode, receiverID);
    }

    @RequestMapping(value = "/new/{bankCode}/{branchCode}/{receiverID}", method = RequestMethod.GET)
    public UnifiedResponse findNewReceiveInfo(@PathVariable("bankCode") String bankCode,
                                           @PathVariable("branchCode") String branchCode,
                                           @PathVariable("receiverID") int receiverID){
        return serviceImpl.findNewReceiveInfo(bankCode, branchCode, receiverID);
    }

    @RequestMapping(value = "/send/{bankCode}/{branchCode}/{senderID}", method = RequestMethod.GET)
    public UnifiedResponse findSendList(@PathVariable("bankCode") String bankCode,
                                                @PathVariable("branchCode") String branchCode,
                                                @PathVariable("senderID") int senderID){
        return serviceImpl.findSendList(bankCode, branchCode, senderID);
    }

    @RequestMapping(method = RequestMethod.POST)
    public UnifiedResponse post(@RequestBody BusinessFlowDTO dto){
        return serviceImpl.add(dto);
    }

    @RequestMapping(value="/changeBusinessStatus", method = RequestMethod.PUT)
    public UnifiedResponse changeBusinessStatus(@RequestBody BusinessFlowDTO dto){
        return serviceImpl.changeBusinessStatus(dto);
    }

    @RequestMapping(value="/completeBusiness", method = RequestMethod.PUT)
    public UnifiedResponse completeBusiness(@RequestBody BusinessFlowDTO dto){
        return serviceImpl.completeBusiness(dto);
    }

    @RequestMapping(value="/addCallBack", method = RequestMethod.PUT)
    public UnifiedResponse addCallBack(@RequestBody BusinessFlowDTO dto){
        return serviceImpl.addCallBack(dto);
    }
}

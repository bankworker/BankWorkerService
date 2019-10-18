package com.johnny.bankworker.controller;

import com.johnny.bankworker.dto.BusinessHurryUpDTO;
import com.johnny.bankworker.service.workerbalance.BusinessHurryUpService;
import com.johnny.bankworker.vo.UnifiedResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/balance/hurryUp")
public class BusinessHurryUpController {
    @Autowired
    private BusinessHurryUpService serviceImpl;

    @RequestMapping(value = "/{bankCode}/{branchCode}/{receiverID}", method = RequestMethod.GET)
    public UnifiedResponse findLatestHurryUp4Receiver(@PathVariable("bankCode") String bankCode,
                                              @PathVariable("branchCode") String branchCode,
                                              @PathVariable("receiverID") int receiverID){
        return serviceImpl.findLatestHurryUp4Receiver(bankCode, branchCode, receiverID);
    }

    @RequestMapping(method = RequestMethod.POST)
    public UnifiedResponse post(@RequestBody BusinessHurryUpDTO dto){
        return serviceImpl.add(dto);
    }

    @RequestMapping(method = RequestMethod.PUT)
    public UnifiedResponse change(@RequestBody BusinessHurryUpDTO dto){
        return serviceImpl.change(dto);
    }
}

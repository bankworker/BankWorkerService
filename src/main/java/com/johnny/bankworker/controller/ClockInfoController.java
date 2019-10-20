package com.johnny.bankworker.controller;

import com.johnny.bankworker.dto.ClockInfoDTO;
import com.johnny.bankworker.service.workerbalance.ClockInfoService;
import com.johnny.bankworker.vo.UnifiedResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/balance/clockInfo")
public class ClockInfoController {
    @Autowired
    private ClockInfoService serviceImpl;

    @RequestMapping(value = "/{bankCode}/{branchCode}/{staffID}", method = RequestMethod.GET)
    public UnifiedResponse findCurrentClockInfo(@PathVariable("bankCode") String bankCode,
                               @PathVariable("branchCode") String branchCode,
                               @PathVariable("staffID") int staffID){
        return serviceImpl.findCurrentClockInfo(bankCode, branchCode, staffID);
    }

    @RequestMapping(value = "/{bankCode}/{branchCode}", method = RequestMethod.GET)
    public UnifiedResponse findClockedFinancialList(@PathVariable("bankCode") String bankCode,
                                                @PathVariable("branchCode") String branchCode){
        return serviceImpl.findClockedFinancialList(bankCode, branchCode);
    }

    @RequestMapping(method = RequestMethod.POST)
    public UnifiedResponse post(@RequestBody ClockInfoDTO dto){
        return serviceImpl.add(dto);
    }
}

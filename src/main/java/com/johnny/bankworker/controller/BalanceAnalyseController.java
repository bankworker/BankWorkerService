package com.johnny.bankworker.controller;

import com.johnny.bankworker.service.impl.workerbalance.BalanceAnalyseImpl;
import com.johnny.bankworker.vo.UnifiedResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/balance/businessAnalyse")
public class BalanceAnalyseController {
    @Autowired
    private BalanceAnalyseImpl serviceImpl;

    @RequestMapping(value = "/financialBusinessAnalyse/{bankCode}/{branchCode}/{fromDate}/{toDate}", method = RequestMethod.GET)
    public UnifiedResponse findFinancialBusinessAnalyse(@PathVariable("bankCode") String bankCode,
                                                        @PathVariable("branchCode") String branchCode,
                                                        @PathVariable("fromDate") String fromDate,
                                                        @PathVariable("toDate") String toDate){
        return serviceImpl.findFinancialBusinessAnalyse(bankCode, branchCode, fromDate, toDate);
    }

    @RequestMapping(value = "/financialCallbackAnalyse/{bankCode}/{branchCode}/{financialID}/{fromDate}/{toDate}", method = RequestMethod.GET)
    public UnifiedResponse findCallbackBusinessAnalyse4Financial(@PathVariable("bankCode") String bankCode,
                                                        @PathVariable("branchCode") String branchCode,
                                                        @PathVariable("financialID") int financialID,
                                                        @PathVariable("fromDate") String fromDate,
                                                        @PathVariable("toDate") String toDate){
        return serviceImpl.findCallbackBusinessAnalyse4Financial(bankCode, branchCode, financialID, fromDate, toDate);
    }

    @RequestMapping(value = "/lobbyBusinessAnalyse/{bankCode}/{branchCode}/{fromDate}/{toDate}", method = RequestMethod.GET)
    public UnifiedResponse findLobbyBusinessAnalyse(@PathVariable("bankCode") String bankCode,
                                                        @PathVariable("branchCode") String branchCode,
                                                        @PathVariable("fromDate") String fromDate,
                                                        @PathVariable("toDate") String toDate){
        return serviceImpl.findLobbyBusinessAnalyse(bankCode, branchCode, fromDate, toDate);
    }

    @RequestMapping(value = "/lobbyCallbackAnalyse/{bankCode}/{branchCode}/{lobbyID}/{fromDate}/{toDate}", method = RequestMethod.GET)
    public UnifiedResponse findCallbackBusinessAnalyse4Lobby(@PathVariable("bankCode") String bankCode,
                                                                 @PathVariable("branchCode") String branchCode,
                                                                 @PathVariable("lobbyID") int lobbyID,
                                                                 @PathVariable("fromDate") String fromDate,
                                                                 @PathVariable("toDate") String toDate){
        return serviceImpl.findCallbackBusinessAnalyse4Lobby(bankCode, branchCode, lobbyID, fromDate, toDate);
    }
}

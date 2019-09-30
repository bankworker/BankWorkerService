package com.johnny.bankworker.controller;

import com.johnny.bankworker.dto.BusinessCallbackDTO;
import com.johnny.bankworker.service.BusinessCallbackService;
import com.johnny.bankworker.vo.UnifiedResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/bank/branch/callback")
public class BusinessCallbackController {
    @Autowired
    private BusinessCallbackService serviceImpl;

    @RequestMapping(value = "/{pageNumber}/{pageSize}/{bankCode}/{branchCode}", method = RequestMethod.GET)
    public UnifiedResponse get(@PathVariable("pageNumber") int pageNumber,
                               @PathVariable("pageSize") int pageSize,
                               @PathVariable("bankCode") String bankCode,
                               @PathVariable("branchCode") String branchCode){
        return serviceImpl.findList4Branch(pageNumber, pageSize, bankCode, branchCode);
    }

    @RequestMapping(value = "/exist/{bankCode}/{branchCode}/{callbackMessage}", method = RequestMethod.GET)
    public UnifiedResponse checkExist(@PathVariable("bankCode") String bankCode,
                                      @PathVariable("branchCode") String branchCode,
                                      @PathVariable("callbackMessage") String callbackMessage){
        return serviceImpl.existCheck(bankCode, branchCode, callbackMessage);
    }

    @RequestMapping(method = RequestMethod.POST)
    public UnifiedResponse post(@RequestBody BusinessCallbackDTO dto){
        return serviceImpl.add(dto);
    }

    @RequestMapping(method = RequestMethod.PUT)
    public UnifiedResponse put(@RequestBody BusinessCallbackDTO dto){
        return serviceImpl.change(dto);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public UnifiedResponse delete(@PathVariable("id") int id){
        return serviceImpl.delete(id);
    }
}

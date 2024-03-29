package com.johnny.bankworker.controller;

import com.johnny.bankworker.dto.BankBranchDTO;
import com.johnny.bankworker.service.impl.BankBranchServiceImpl;
import com.johnny.bankworker.vo.UnifiedResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/bank/branch")
public class BankBranchController {
    @Autowired
    private BankBranchServiceImpl serviceImpl;

    @RequestMapping(value = "/{pageNumber}/{pageSize}/{dataStatus}", method = RequestMethod.GET)
    public UnifiedResponse findList(@PathVariable("pageNumber") int pageNumber, @PathVariable("pageSize") int pageSize, @PathVariable("dataStatus") String dataStatus){
        return serviceImpl.findList(pageNumber, pageSize, dataStatus);
    }

    @RequestMapping(value = "/{bankCode}", method = RequestMethod.GET)
    public UnifiedResponse findByBankCode(@PathVariable("bankCode") String bankCode){
        return serviceImpl.findListByBankCode(bankCode);
    }

    @RequestMapping(value = "/code/{bankCode}/{branchCode}", method = RequestMethod.GET)
    public UnifiedResponse findByCode(@PathVariable("bankCode") String bankCode, @PathVariable("branchCode") String branchCode){
        return serviceImpl.findListByCode(bankCode, branchCode);
    }

    @RequestMapping(value = "/{id}/{dataStatus}", method = RequestMethod.GET)
    public UnifiedResponse find(@PathVariable("id") int id, @PathVariable("dataStatus") String dataStatus){
        return serviceImpl.find(id, dataStatus);
    }

    @RequestMapping(value = "/exist/{value}", method = RequestMethod.GET)
    public UnifiedResponse checkExist(@PathVariable("value") String value){
        return serviceImpl.existCheck(value);
    }

    @RequestMapping(method = RequestMethod.POST)
    public UnifiedResponse add(@RequestBody BankBranchDTO dto){
        return serviceImpl.add(dto);
    }

    @RequestMapping(method = RequestMethod.PUT)
    public UnifiedResponse change(@RequestBody BankBranchDTO dto){
        return serviceImpl.change(dto);
    }

    @RequestMapping(value="/changeStatus", method = RequestMethod.PUT)
    public UnifiedResponse changeStatus(@RequestBody BankBranchDTO dto){
        return serviceImpl.changeDataStatus(dto);
    }

    @RequestMapping(value="/changeLogo", method = RequestMethod.PUT)
    public UnifiedResponse changeLogo(@RequestBody BankBranchDTO dto){
        return serviceImpl.changeLogo(dto);
    }

    @RequestMapping(value="/changeBackImage", method = RequestMethod.PUT)
    public UnifiedResponse changeBackImage(@RequestBody BankBranchDTO dto){
        return serviceImpl.changeBackImage(dto);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public UnifiedResponse delete(@PathVariable("id") int id){
        return serviceImpl.delete(id);
    }
}

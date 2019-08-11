package com.johnny.bankworker.controller;

import com.johnny.bankworker.dto.BankBranchStaffAccount4OriginalDTO;
import com.johnny.bankworker.service.impl.BankBranchStaffAccountServiceImpl;
import com.johnny.bankworker.vo.UnifiedResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/system/account")
public class BankBranchStaffAccountController {
    @Autowired
    private BankBranchStaffAccountServiceImpl serviceImpl;

    @RequestMapping(value = "/{pageNumber}/{pageSize}/{dataStatus}", method = RequestMethod.GET)
    public UnifiedResponse findList(@PathVariable("pageNumber") int pageNumber, @PathVariable("pageSize") int pageSize, @PathVariable("dataStatus") String dataStatus){
        return serviceImpl.findList(pageNumber, pageSize, dataStatus);
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
    public UnifiedResponse add(@RequestBody BankBranchStaffAccount4OriginalDTO dto){
        return serviceImpl.add(dto);
    }

    @RequestMapping(method = RequestMethod.PUT)
    public UnifiedResponse change(@RequestBody BankBranchStaffAccount4OriginalDTO dto){
        return serviceImpl.change(dto);
    }

    @RequestMapping(value="/changeStatus", method = RequestMethod.PUT)
    public UnifiedResponse changeStatus(@RequestBody BankBranchStaffAccount4OriginalDTO dto){
        return serviceImpl.changeDataStatus(dto);
    }

    @RequestMapping(method = RequestMethod.DELETE)
    public UnifiedResponse delete(@RequestBody BankBranchStaffAccount4OriginalDTO dto){
        return serviceImpl.delete(dto);
    }
}

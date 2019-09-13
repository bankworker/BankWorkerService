package com.johnny.bankworker.controller;

import com.johnny.bankworker.dto.BankBranchStaffDTO;
import com.johnny.bankworker.service.impl.BankBranchStaffServiceImpl;
import com.johnny.bankworker.vo.UnifiedResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/bank/branch/staff")
public class BankBranchStaffController {
    @Autowired
    private BankBranchStaffServiceImpl serviceImpl;

    @RequestMapping(value = "/{pageNumber}/{pageSize}/{bankCode}/{branchCode}", method = RequestMethod.GET)
    public UnifiedResponse findList(@PathVariable("pageNumber") int pageNumber,
                                    @PathVariable("pageSize") int pageSize,
                                    @PathVariable("bankCode") String bankCode,
                                    @PathVariable("branchCode") String branchCode){
        return serviceImpl.findList4Branch(pageNumber, pageSize, bankCode, branchCode);
    }

    @RequestMapping(value = "/{id}/{dataStatus}", method = RequestMethod.GET)
    public UnifiedResponse find(@PathVariable("id") int id,
                                @PathVariable("dataStatus") String dataStatus){
        return serviceImpl.find(id, dataStatus);
    }

    @RequestMapping(value = "/exist/{cellphone}", method = RequestMethod.GET)
    public UnifiedResponse checkExist(@PathVariable("cellphone") String cellphone){
        return serviceImpl.existCheck(cellphone);
    }

    @RequestMapping(method = RequestMethod.POST)
    public UnifiedResponse add(@RequestBody BankBranchStaffDTO dto){
        return serviceImpl.add(dto);
    }

    @RequestMapping(method = RequestMethod.PUT)
    public UnifiedResponse change(@RequestBody BankBranchStaffDTO dto){
        return serviceImpl.change(dto);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public UnifiedResponse delete(@PathVariable("id") int id){
        return serviceImpl.delete(id);
    }
}

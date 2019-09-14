package com.johnny.bankworker.controller;

import com.johnny.bankworker.constant.DataStatusConstant;
import com.johnny.bankworker.dto.BranchStaffPostDTO;
import com.johnny.bankworker.service.impl.BranchStaffPostServiceImpl;
import com.johnny.bankworker.vo.UnifiedResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/bank/branch/staffPost")
public class BranchStaffPostController {
    @Autowired
    private BranchStaffPostServiceImpl serviceImpl;

    @RequestMapping(value = "/{pageNumber}/{pageSize}/{bankCode}/{branchCode}", method = RequestMethod.GET)
    public UnifiedResponse findList(@PathVariable("pageNumber") int pageNumber,
                                    @PathVariable("pageSize") int pageSize,
                                    @PathVariable("bankCode") String bankCode,
                                    @PathVariable("branchCode") String branchCode){
        return serviceImpl.findList(pageNumber, pageSize, bankCode, branchCode);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public UnifiedResponse find(@PathVariable("id") int id){
        return serviceImpl.find(id, DataStatusConstant.Normal);
    }

    @RequestMapping(value = "/exist/{bankCode}/{branchCode}/{staffName}", method = RequestMethod.GET)
    public UnifiedResponse checkExist(@PathVariable("bankCode") String bankCode,
                                      @PathVariable("branchCode") String branchCode,
                                      @PathVariable("staffName") String staffName){
        return serviceImpl.existCheck(bankCode, branchCode, staffName);
    }

    @RequestMapping(value = "/isUsing/{bankCode}/{branchCode}/{staffPostID}", method = RequestMethod.GET)
    public UnifiedResponse checkStaffPostIsUsing(@PathVariable("bankCode") String bankCode,
                                      @PathVariable("branchCode") String branchCode,
                                      @PathVariable("staffPostID") int staffPostID){
        return serviceImpl.checkStaffPostIsUsing(bankCode, branchCode, staffPostID);
    }

    @RequestMapping(method = RequestMethod.POST)
    public UnifiedResponse add(@RequestBody BranchStaffPostDTO dto){
        return serviceImpl.add(dto);
    }

    @RequestMapping(method = RequestMethod.PUT)
    public UnifiedResponse change(@RequestBody BranchStaffPostDTO dto){
        return serviceImpl.change(dto);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public UnifiedResponse delete(@PathVariable("id") int id){
        return serviceImpl.delete(id);
    }
}

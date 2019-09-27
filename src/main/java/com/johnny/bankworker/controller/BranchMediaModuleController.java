package com.johnny.bankworker.controller;

import com.johnny.bankworker.dto.MediaModuleDTO;
import com.johnny.bankworker.dto.MediaModuleDetailDTO;
import com.johnny.bankworker.service.BranchMediaModuleService;
import com.johnny.bankworker.vo.UnifiedResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/bank/branch/mediaModule")
public class BranchMediaModuleController {
    @Autowired
    private BranchMediaModuleService serviceImpl;

    @RequestMapping(value = "/{pageNumber}/{pageSize}/{bankCode}/{branchCode}", method = RequestMethod.GET)
    public UnifiedResponse findList4Branch(@PathVariable("pageNumber") int pageNumber,
                                    @PathVariable("pageSize") int pageSize,
                                    @PathVariable("bankCode") String bankCode,
                                    @PathVariable("branchCode") String branchCode){
        return serviceImpl.findList4Branch(pageNumber, pageSize, bankCode, branchCode);
    }

    @RequestMapping(value = "/{bankCode}/{branchCode}/{mediaModuleID}", method = RequestMethod.GET)
    public UnifiedResponse find(@PathVariable("bankCode") String bankCode,
                                           @PathVariable("branchCode") String branchCode,
                                           @PathVariable("mediaModuleID") int mediaModuleID){
        return serviceImpl.find(bankCode, branchCode, mediaModuleID);
    }

    @RequestMapping(value="/changeMediaModuleOrder", method = RequestMethod.PUT)
    public UnifiedResponse changeMediaModuleOrder(@RequestBody MediaModuleDTO dto){
        return serviceImpl.changeMediaModuleOrder(dto);
    }

    @RequestMapping(value="/changeMediaModuleDetailOrder", method = RequestMethod.PUT)
    public UnifiedResponse changeMediaModuleDetailOrder(@RequestBody MediaModuleDetailDTO dto){
        return serviceImpl.changeMediaModuleDetailOrder(dto);
    }

    @RequestMapping(method = RequestMethod.POST)
    public UnifiedResponse add(@RequestBody MediaModuleDTO dto){
        return serviceImpl.add(dto);
    }
    @RequestMapping(method = RequestMethod.PUT)
    public UnifiedResponse change(@RequestBody MediaModuleDTO dto){
        return serviceImpl.change(dto);
    }

    @RequestMapping(value="/changeDataStatus", method = RequestMethod.PUT)
    public UnifiedResponse changeDataStatus(@RequestBody MediaModuleDTO dto){
        return serviceImpl.changeDataStatus(dto);
    }

    @RequestMapping(value = "/{bankCode}/{branchCode}/{mediaModuleID}", method = RequestMethod.DELETE)
    public UnifiedResponse deleteMediaModule(@PathVariable("bankCode") String bankCode,
                                  @PathVariable("branchCode") String branchCode,
                                  @PathVariable("mediaModuleID") int mediaModuleID){
        return serviceImpl.deleteMediaModule(bankCode, branchCode, mediaModuleID);
    }

    @RequestMapping(value = "/{bankCode}/{branchCode}/{mediaModuleID}/{mediaDetailID}", method = RequestMethod.DELETE)
    public UnifiedResponse deleteMediaModuleDetail(@PathVariable("bankCode") String bankCode,
                                                   @PathVariable("branchCode") String branchCode,
                                                   @PathVariable("mediaModuleID") int mediaModuleID,
                                                   @PathVariable("mediaDetailID") int mediaDetailID){
        return serviceImpl.deleteMediaModuleDetail(bankCode, branchCode, mediaModuleID, mediaDetailID);
    }
}

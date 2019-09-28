package com.johnny.bankworker.controller;

import com.johnny.bankworker.dto.BranchArchiveDTO;
import com.johnny.bankworker.service.impl.BranchArchiveServiceImpl;
import com.johnny.bankworker.vo.UnifiedResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/bank/branch/archive")
public class BranchArchiveController {
    @Autowired
    private BranchArchiveServiceImpl serviceImpl;

    @RequestMapping(value = "/{pageNumber}/{pageSize}/{bankCode}/{branchCode}", method = RequestMethod.GET)
    public UnifiedResponse findList(@PathVariable("pageNumber") int pageNumber,
                                    @PathVariable("pageSize") int pageSize,
                                    @PathVariable("bankCode") String bankCode,
                                    @PathVariable("branchCode") String branchCode){
        return serviceImpl.findList(pageNumber, pageSize, bankCode, branchCode);
    }

    @RequestMapping(value = "/exist/{bankCode}/{branchCode}/{archiveName}", method = RequestMethod.GET)
    public UnifiedResponse checkExist(@PathVariable("bankCode") String bankCode,
                                      @PathVariable("branchCode") String branchCode,
                                      @PathVariable("archiveName") String archiveName){
        return serviceImpl.existCheck(bankCode, branchCode, archiveName);
    }

    @RequestMapping(value = "/hasChildArchive/{bankCode}/{branchCode}/{archiveParentID}", method = RequestMethod.GET)
    public UnifiedResponse hasChildArchive(@PathVariable("bankCode") String bankCode,
                                      @PathVariable("branchCode") String branchCode,
                                      @PathVariable("archiveParentID") int archiveParentID){
        return serviceImpl.existCheck(bankCode, branchCode, archiveParentID);
    }

    @RequestMapping(method = RequestMethod.POST)
    public UnifiedResponse add(@RequestBody BranchArchiveDTO dto){
        return serviceImpl.add(dto);
    }

    @RequestMapping(method = RequestMethod.PUT)
    public UnifiedResponse change(@RequestBody BranchArchiveDTO dto){
        return serviceImpl.change(dto);
    }

    @RequestMapping(value="/changeOrder", method = RequestMethod.PUT)
    public UnifiedResponse changeOrder(@RequestBody BranchArchiveDTO dto){
        return serviceImpl.changeOrder(dto);
    }

    @RequestMapping(value="/changeStatus", method = RequestMethod.PUT)
    public UnifiedResponse changeStatus(@RequestBody BranchArchiveDTO dto){
        return serviceImpl.changeDataStatus(dto);
    }

    @RequestMapping(value="/changePosition", method = RequestMethod.PUT)
    public UnifiedResponse changePosition(@RequestBody BranchArchiveDTO dto){
        return serviceImpl.changePosition(dto);
    }

    @RequestMapping(value = "/{bankCode}/{branchCode}/{archiveID}", method = RequestMethod.DELETE)
    public UnifiedResponse delete(@PathVariable("bankCode") String bankCode,
                                  @PathVariable("branchCode") String branchCode,
                                  @PathVariable("archiveID") int archiveID){
        return serviceImpl.delete(bankCode, branchCode, archiveID);
    }
}

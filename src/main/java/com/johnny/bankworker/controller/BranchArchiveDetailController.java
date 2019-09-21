package com.johnny.bankworker.controller;

import com.johnny.bankworker.dto.BranchArchiveDetailDTO;
import com.johnny.bankworker.service.impl.BranchArchiveDetailServiceImpl;
import com.johnny.bankworker.vo.UnifiedResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/bank/branch/archive/detail")
public class BranchArchiveDetailController {
    @Autowired
    private BranchArchiveDetailServiceImpl serviceImpl;

    @RequestMapping(value = "/{bankCode}/{branchCode}/{archiveID}", method = RequestMethod.GET)
    public UnifiedResponse findList(@PathVariable("bankCode") String bankCode,
                                    @PathVariable("branchCode") String branchCode,
                                    @PathVariable("archiveID") int archiveID){
        return serviceImpl.findList(bankCode, branchCode, archiveID);
    }

    @RequestMapping(method = RequestMethod.POST)
    public UnifiedResponse add(@RequestBody BranchArchiveDetailDTO dto){
        return serviceImpl.add(dto);
    }

    @RequestMapping(value="/changePosition", method = RequestMethod.PUT)
    public UnifiedResponse changePosition(@RequestBody BranchArchiveDetailDTO dto){
        return serviceImpl.changeOrder(dto);
    }

    @RequestMapping(value = "/deleteOfArchive/{bankCode}/{branchCode}/{archiveID}", method = RequestMethod.DELETE)
    public UnifiedResponse deleteOfArchive(@PathVariable("bankCode") String bankCode,
                                           @PathVariable("branchCode") String branchCode,
                                           @PathVariable("archiveID") int archiveID){
        return serviceImpl.deleteOfArchive(bankCode, branchCode, archiveID);
    }

    @RequestMapping(value = "/deleteArchiveDetail/{bankCode}/{branchCode}/{archiveID}/{archiveDetailID}", method = RequestMethod.DELETE)
    public UnifiedResponse deleteOfArchiveDetail(@PathVariable("bankCode") String bankCode,
                                                 @PathVariable("branchCode") String branchCode,
                                                 @PathVariable("archiveID") int archiveID,
                                                 @PathVariable("archiveDetailID") int archiveDetailID){
        return serviceImpl.deleteOfArchiveDetail(bankCode, branchCode, archiveID, archiveDetailID);
    }
}

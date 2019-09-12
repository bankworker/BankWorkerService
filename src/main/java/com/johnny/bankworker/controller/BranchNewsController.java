package com.johnny.bankworker.controller;

import com.johnny.bankworker.dto.BranchNewsDTO;
import com.johnny.bankworker.service.BranchNewsService;
import com.johnny.bankworker.vo.UnifiedResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/news")
public class BranchNewsController {
    @Autowired
    private BranchNewsService serviceImpl;

    @RequestMapping(value = "/{pageNumber}/{pageSize}/{bankCode}/{branchCode}", method = RequestMethod.GET)
    public UnifiedResponse get(@PathVariable("pageNumber") int pageNumber,
                               @PathVariable("pageSize") int pageSize,
                               @PathVariable("bankCode") String bankCode,
                               @PathVariable("branchCode") String branchCode){
        return serviceImpl.findList4Branch(pageNumber, pageSize, bankCode, branchCode);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public UnifiedResponse get(@PathVariable("id") int id){
        return serviceImpl.find(id);
    }


    @RequestMapping(method = RequestMethod.POST)
    public UnifiedResponse post(@RequestBody BranchNewsDTO dto){
        return serviceImpl.add(dto);
    }

    @RequestMapping(method = RequestMethod.PUT)
    public UnifiedResponse put(@RequestBody BranchNewsDTO dto){
        return serviceImpl.change(dto);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public UnifiedResponse delete(@PathVariable("id") int id){
        return serviceImpl.delete(id);
    }
}

package com.johnny.bankworker.controller;

import com.johnny.bankworker.service.impl.ChinaMappingServiceImpl;
import com.johnny.bankworker.vo.UnifiedResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/chinaMapping")
public class ChinaMappingController {
    @Autowired
    private ChinaMappingServiceImpl serviceImpl;

    @RequestMapping(value = "/{parentCode}", method = RequestMethod.GET)
    public UnifiedResponse find(@PathVariable("parentCode") int parentCode){
        return serviceImpl.searchByParentCode(parentCode);
    }
}

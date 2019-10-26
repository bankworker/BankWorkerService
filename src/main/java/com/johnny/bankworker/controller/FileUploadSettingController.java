package com.johnny.bankworker.controller;

import com.johnny.bankworker.dto.FileUploadServiceSettingDTO;
import com.johnny.bankworker.service.impl.FileUploadSettingServiceImpl;
import com.johnny.bankworker.vo.UnifiedResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/serviceSetting")
public class FileUploadSettingController {
    @Autowired
    private FileUploadSettingServiceImpl serviceImpl;

    @RequestMapping(value = "/{pageNumber}/{pageSize}", method = RequestMethod.GET)
    public UnifiedResponse findList(@PathVariable("pageNumber") int pageNumber,
                                    @PathVariable("pageSize") int pageSize){
        return serviceImpl.findList(pageNumber, pageSize, "A");
    }

    @RequestMapping(value = "/branch/{bankCode}/{branchCode}", method = RequestMethod.GET)
    public UnifiedResponse findUploadService(@PathVariable("bankCode") String bankCode,
                                @PathVariable("branchCode") String branchCode){
        return serviceImpl.findList4Branch(1, 999, bankCode, branchCode);
    }

    @RequestMapping(value = "/exist/{uploadUrl}", method = RequestMethod.GET)
    public UnifiedResponse checkExist(@PathVariable("uploadUrl") String uploadUrl){
        return serviceImpl.existCheck(uploadUrl);
    }

    @RequestMapping(method = RequestMethod.POST)
    public UnifiedResponse add(@RequestBody FileUploadServiceSettingDTO dto){
        return serviceImpl.add(dto);
    }

    @RequestMapping(method = RequestMethod.PUT)
    public UnifiedResponse change(@RequestBody FileUploadServiceSettingDTO dto){
        return serviceImpl.change(dto);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public UnifiedResponse delete(@PathVariable("id") int id){
        return serviceImpl.delete(id);
    }
}

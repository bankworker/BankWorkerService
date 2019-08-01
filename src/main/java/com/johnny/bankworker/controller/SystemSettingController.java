package com.johnny.bankworker.controller;

import com.johnny.bankworker.dto.SystemSettingDTO;
import com.johnny.bankworker.service.impl.SystemSettingServiceImpl;
import com.johnny.bankworker.vo.UnifiedResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/systemSetting")
public class SystemSettingController {
    @Autowired
    private SystemSettingServiceImpl serviceImpl;
    @RequestMapping(value = "/{pageNumber}/{pageSize}/{dataStatus}", method = RequestMethod.GET)
    public UnifiedResponse findList(@PathVariable("pageNumber") int pageNumber, @PathVariable("pageSize") int pageSize, @PathVariable("dataStatus") String dataStatus){
        return serviceImpl.findList(pageNumber, pageSize, dataStatus);
    }

    @RequestMapping(value = "/{id}/{dataStatus}", method = RequestMethod.GET)
    public UnifiedResponse find(@PathVariable("id") int id, @PathVariable("dataStatus") String dataStatus){
        return serviceImpl.find(id, dataStatus);
    }

    @RequestMapping(method = RequestMethod.POST)
    public UnifiedResponse add(@RequestBody SystemSettingDTO dto){
        return serviceImpl.add(dto);
    }

    @RequestMapping(method = RequestMethod.PUT)
    public UnifiedResponse change(@RequestBody SystemSettingDTO dto){
        return serviceImpl.change(dto);
    }

    @RequestMapping(value="/changeStatus", method = RequestMethod.PUT)
    public UnifiedResponse changeStatus(@RequestBody SystemSettingDTO dto){
        return serviceImpl.changeDataStatus(dto);
    }

    @RequestMapping(method = RequestMethod.DELETE)
    public UnifiedResponse delete(@RequestBody SystemSettingDTO dto){
        return serviceImpl.delete(dto);
    }

}

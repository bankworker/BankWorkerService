package com.johnny.bankworker.controller;

import com.johnny.bankworker.dto.ServiceSettingDTO;
import com.johnny.bankworker.service.impl.ServiceSettingServiceImpl;
import com.johnny.bankworker.vo.UnifiedResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/service/setting")
public class ServiceSettingController {
    @Autowired
    private ServiceSettingServiceImpl serviceImpl;
    @RequestMapping(value = "/{pageNumber}/{pageSize}/{dataStatus}", method = RequestMethod.GET)
    public UnifiedResponse findList(@PathVariable("pageNumber") int pageNumber, @PathVariable("pageSize") int pageSize, @PathVariable("dataStatus") String dataStatus){
        return serviceImpl.findList(pageNumber, pageSize, dataStatus);
    }

    @RequestMapping(value = "/{id}/{dataStatus}", method = RequestMethod.GET)
    public UnifiedResponse find(@PathVariable("id") int id, @PathVariable("dataStatus") String dataStatus){
        return serviceImpl.find(id, dataStatus);
    }

    @RequestMapping(method = RequestMethod.POST)
    public UnifiedResponse add(@RequestBody ServiceSettingDTO dto){
        return serviceImpl.add(dto);
    }

    @RequestMapping(method = RequestMethod.PUT)
    public UnifiedResponse change(@RequestBody ServiceSettingDTO dto){
        return serviceImpl.change(dto);
    }

    @RequestMapping(value="/changeStatus", method = RequestMethod.PUT)
    public UnifiedResponse changeStatus(@RequestBody ServiceSettingDTO dto){
        return serviceImpl.changeDataStatus(dto);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public UnifiedResponse delete(@PathVariable("id") int id){
        return serviceImpl.delete(id);
    }
}

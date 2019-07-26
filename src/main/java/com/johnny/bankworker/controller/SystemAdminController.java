package com.johnny.bankworker.controller;

import com.johnny.bankworker.service.impl.SystemAdminServiceImpl;
import com.johnny.bankworker.vo.UnifiedResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/admin")
public class SystemAdminController {
    @Autowired
    private SystemAdminServiceImpl serviceImpl;

    @RequestMapping(value = "/login/{cellphone}/{password}", method = RequestMethod.GET)
    public UnifiedResponse login(@PathVariable("cellphone") String cellphone, @PathVariable("password") String password){
        return serviceImpl.login(cellphone, password);
    }
}

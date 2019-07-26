package com.johnny.bankworker.service.impl;

import com.johnny.bankworker.common.ObjectConvertUtils;
import com.johnny.bankworker.constant.ResponseDataConstant;
import com.johnny.bankworker.dto.SystemAdminDTO;
import com.johnny.bankworker.entity.SystemAdminEntity;
import com.johnny.bankworker.manager.UnifiedResponseManager;
import com.johnny.bankworker.mapper.SystemAdminMapper;
import com.johnny.bankworker.service.SystemAdminService;
import com.johnny.bankworker.vo.SystemAdminVO;
import com.johnny.bankworker.vo.UnifiedResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SystemAdminServiceImpl implements SystemAdminService {
    @Autowired
    private SystemAdminMapper myMapper;
    private Logger logger = LogManager.getLogger(SystemAdminServiceImpl.class);

    @Override
    public UnifiedResponse login(String cellphone, String password) {
        try {
            SystemAdminVO model = new SystemAdminVO();
            SystemAdminEntity entity =  myMapper.login(cellphone, password);
            if(entity == null){
                return UnifiedResponseManager.buildSearchSuccessResponse(ResponseDataConstant.NO_SEARCH_COUNT, ResponseDataConstant.NO_DATA);
            }
            ObjectConvertUtils.toBean(entity, model);
            return UnifiedResponseManager.buildSearchSuccessResponse(1, model);
        } catch (Exception ex) {
            logger.error(ex.toString());
            return UnifiedResponseManager.buildExceptionResponse();
        }
    }

    @Override
    public UnifiedResponse changePassword(SystemAdminDTO dto) {
        return null;
    }

    @Override
    public UnifiedResponse findList(int pageNumber, int pageSize, String dataStatus) {
        return null;
    }

    @Override
    public UnifiedResponse find(int id, String dataStatus) {
        return null;
    }

    @Override
    public UnifiedResponse existCheck(String value) {
        return null;
    }

    @Override
    public UnifiedResponse add(SystemAdminDTO dto) {
        return null;
    }

    @Override
    public UnifiedResponse change(SystemAdminDTO dto) {
        return null;
    }

    @Override
    public UnifiedResponse changeDataStatus(SystemAdminDTO dto) {
        return null;
    }

    @Override
    public UnifiedResponse delete(SystemAdminDTO dto) {
        return null;
    }
}

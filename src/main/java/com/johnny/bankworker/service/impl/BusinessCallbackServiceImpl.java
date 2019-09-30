package com.johnny.bankworker.service.impl;

import com.johnny.bankworker.common.ObjectConvertUtils;
import com.johnny.bankworker.constant.ResponseDataConstant;
import com.johnny.bankworker.dto.BusinessCallbackDTO;
import com.johnny.bankworker.entity.BusinessCallbackEntity;
import com.johnny.bankworker.manager.UnifiedResponseManager;
import com.johnny.bankworker.mapper.BusinessCallbackMapper;
import com.johnny.bankworker.service.BusinessCallbackService;
import com.johnny.bankworker.vo.BusinessCallbackVO;
import com.johnny.bankworker.vo.UnifiedResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BusinessCallbackServiceImpl implements BusinessCallbackService {
    @Autowired
    private BusinessCallbackMapper myMapper;
    private Logger logger = LogManager.getLogger(BusinessCallbackServiceImpl.class);

    @Override
    public UnifiedResponse findList4Branch(int pageNumber, int pageSize, String bankCode, String branchCode) {
        try {
            int startIndex = (pageNumber - 1) * pageSize;
            List<BusinessCallbackVO> modelList = new ArrayList<>();
            int totalCount = myMapper.searchTotalCount4Branch(bankCode, branchCode);
            if(totalCount == 0){
                return UnifiedResponseManager.buildSearchSuccessResponse(ResponseDataConstant.NO_SEARCH_COUNT, ResponseDataConstant.NO_DATA);
            }
            List<BusinessCallbackEntity> entityList =  myMapper.searchList4Branch(startIndex, pageSize, bankCode, branchCode);
            for (BusinessCallbackEntity entity : entityList) {
                BusinessCallbackVO model = new BusinessCallbackVO();
                ObjectConvertUtils.toBean(entity, model);
                modelList.add(model);
            }
            return UnifiedResponseManager.buildSearchSuccessResponse(totalCount, modelList);
        } catch (Exception ex) {
            logger.error(ex.toString());
            return UnifiedResponseManager.buildExceptionResponse();
        }
    }

    @Override
    public UnifiedResponse existCheck(String bankCode, String branchCode, String callbackMessage) {
        try {
            int totalCount = myMapper.existCheck4Branch(bankCode, branchCode, callbackMessage);
            return UnifiedResponseManager.buildSearchSuccessResponse(totalCount, totalCount);
        } catch (Exception ex) {
            logger.error(ex.toString());
            return UnifiedResponseManager.buildExceptionResponse();
        }
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
    public UnifiedResponse add(BusinessCallbackDTO dto) {
        try {
            BusinessCallbackEntity branchNewsEntity = new BusinessCallbackEntity();
            ObjectConvertUtils.toBean(dto, branchNewsEntity);
            branchNewsEntity.setCreateUser(dto.getLoginUser());
            branchNewsEntity.setUpdateUser(dto.getLoginUser());
            int affectRow = myMapper.insert(branchNewsEntity);
            return UnifiedResponseManager.buildSubmitSuccessResponse(affectRow);
        } catch (Exception ex) {
            logger.error(ex.toString());
            return UnifiedResponseManager.buildExceptionResponse();
        }
    }

    @Override
    public UnifiedResponse change(BusinessCallbackDTO dto) {
        try {
            BusinessCallbackEntity branchNewsEntity = new BusinessCallbackEntity();
            ObjectConvertUtils.toBean(dto, branchNewsEntity);
            branchNewsEntity.setUpdateUser(dto.getLoginUser());
            int affectRow = myMapper.update(branchNewsEntity);
            return UnifiedResponseManager.buildSubmitSuccessResponse(affectRow);
        } catch (Exception ex) {
            logger.error(ex.toString());
            return UnifiedResponseManager.buildExceptionResponse();
        }
    }

    @Override
    public UnifiedResponse changeDataStatus(BusinessCallbackDTO dto) {
        return null;
    }

    @Override
    public UnifiedResponse delete(int id) {
        try {
            int affectRow = myMapper.delete(id);
            return UnifiedResponseManager.buildSubmitSuccessResponse(affectRow);
        } catch (Exception ex) {
            logger.error(ex.toString());
            return UnifiedResponseManager.buildExceptionResponse();
        }
    }
}

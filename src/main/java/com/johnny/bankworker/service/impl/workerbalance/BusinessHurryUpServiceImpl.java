package com.johnny.bankworker.service.impl.workerbalance;

import com.johnny.bankworker.common.ObjectConvertUtils;
import com.johnny.bankworker.constant.ResponseDataConstant;
import com.johnny.bankworker.dto.BusinessHurryUpDTO;
import com.johnny.bankworker.entity.BusinessHurryUpEntity;
import com.johnny.bankworker.manager.UnifiedResponseManager;
import com.johnny.bankworker.mapper.workerbalance.BusinessHurryUpMapper;
import com.johnny.bankworker.service.workerbalance.BusinessHurryUpService;
import com.johnny.bankworker.vo.BusinessHurryUpVO;
import com.johnny.bankworker.vo.UnifiedResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BusinessHurryUpServiceImpl implements BusinessHurryUpService {
    @Autowired
    private BusinessHurryUpMapper myMapper;
    private Logger logger = LogManager.getLogger(BusinessHurryUpServiceImpl.class);

    @Override
    public UnifiedResponse findLatestHurryUp4Receiver(String bankCode, String branchCode, int receiverID) {
        try {
            BusinessHurryUpVO model = new BusinessHurryUpVO();
            BusinessHurryUpEntity entity =  myMapper.searchLatestHurryUp4Receiver(bankCode, branchCode, receiverID);
            if(entity == null){
                return UnifiedResponseManager.buildSearchSuccessResponse(ResponseDataConstant.NO_SEARCH_COUNT, ResponseDataConstant.NO_DATA);
            }
            ObjectConvertUtils.toBean(entity, model);
            return UnifiedResponseManager.buildSearchSuccessResponse(ResponseDataConstant.ONE_SEARCH_COUNT, model);
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
    public UnifiedResponse add(BusinessHurryUpDTO dto) {
        try {
            BusinessHurryUpEntity entity = new BusinessHurryUpEntity();
            ObjectConvertUtils.toBean(dto, entity);
            entity.setCreateUser(dto.getLoginUser());
            entity.setUpdateUser(dto.getLoginUser());
            int affectRow = myMapper.insert(entity);
            return UnifiedResponseManager.buildSubmitSuccessResponse(affectRow);
        } catch (Exception ex) {
            logger.error(ex.toString());
            return UnifiedResponseManager.buildExceptionResponse();
        }
    }

    @Override
    public UnifiedResponse change(BusinessHurryUpDTO dto) {
        try {
            BusinessHurryUpEntity entity = new BusinessHurryUpEntity();
            ObjectConvertUtils.toBean(dto, entity);
            entity.setUpdateUser(dto.getLoginUser());
            int affectRow = myMapper.update(entity);
            return UnifiedResponseManager.buildSubmitSuccessResponse(affectRow);
        } catch (Exception ex) {
            logger.error(ex.toString());
            return UnifiedResponseManager.buildExceptionResponse();
        }
    }

    @Override
    public UnifiedResponse changeDataStatus(BusinessHurryUpDTO dto) {
        return null;
    }

    @Override
    public UnifiedResponse delete(int id) {
        return null;
    }
}

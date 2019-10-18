package com.johnny.bankworker.service.impl.workerbalance;

import com.johnny.bankworker.common.DateUtils;
import com.johnny.bankworker.common.ObjectConvertUtils;
import com.johnny.bankworker.constant.BalanceBusinessStatusConstant;
import com.johnny.bankworker.constant.ResponseDataConstant;
import com.johnny.bankworker.dto.BusinessFlowDTO;
import com.johnny.bankworker.entity.BusinessFlowEntity;
import com.johnny.bankworker.manager.UnifiedResponseManager;
import com.johnny.bankworker.mapper.workerbalance.BusinessFlowMapper;
import com.johnny.bankworker.service.workerbalance.BusinessFlowService;
import com.johnny.bankworker.vo.UnifiedResponse;
import com.johnny.bankworker.vo.BusinessFlowVO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BusinessFlowServiceImpl implements BusinessFlowService {
    @Autowired
    private BusinessFlowMapper myMapper;
    private Logger logger = LogManager.getLogger(BusinessFlowServiceImpl.class);

    @Override
    public UnifiedResponse findReceiveList(String bankCode, String branchCode, int receiverID) {
        try {
            List<BusinessFlowVO> modelList = new ArrayList<>();
            List<BusinessFlowEntity> entityList =  myMapper.searchReceiveList(bankCode, branchCode, receiverID);
            if(entityList.isEmpty()){
                return UnifiedResponseManager.buildSearchSuccessResponse(ResponseDataConstant.NO_SEARCH_COUNT, ResponseDataConstant.NO_DATA);
            }
            for (BusinessFlowEntity entity : entityList) {
                BusinessFlowVO model = new BusinessFlowVO();
                ObjectConvertUtils.toBean(entity, model);
                modelList.add(model);
            }
            return UnifiedResponseManager.buildSearchSuccessResponse(modelList.size(), modelList);
        } catch (Exception ex) {
            logger.error(ex.toString());
            return UnifiedResponseManager.buildExceptionResponse();
        }
    }

    @Override
    public UnifiedResponse findNewReceiveInfo(String bankCode, String branchCode, int receiverID) {
        try {
            BusinessFlowVO model = new BusinessFlowVO();
            BusinessFlowEntity entity =  myMapper.searchNewReceiveInfo(bankCode, branchCode, receiverID);
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
    public UnifiedResponse findSendList(String bankCode, String branchCode, int senderID) {
        try {
            List<BusinessFlowVO> modelList = new ArrayList<>();
            List<BusinessFlowEntity> entityList =  myMapper.searchSendList(bankCode, branchCode, senderID);
            if(entityList.isEmpty()){
                return UnifiedResponseManager.buildSearchSuccessResponse(ResponseDataConstant.NO_SEARCH_COUNT, ResponseDataConstant.NO_DATA);
            }
            for (BusinessFlowEntity entity : entityList) {
                BusinessFlowVO model = new BusinessFlowVO();
                ObjectConvertUtils.toBean(entity, model);
                modelList.add(model);
            }
            return UnifiedResponseManager.buildSearchSuccessResponse(modelList.size(), modelList);
        } catch (Exception ex) {
            logger.error(ex.toString());
            return UnifiedResponseManager.buildExceptionResponse();
        }
    }

    @Override
    public UnifiedResponse changeBusinessStatus(BusinessFlowDTO dto) {
        try {
            BusinessFlowEntity entity = new BusinessFlowEntity();
            ObjectConvertUtils.toBean(dto, entity);
            if(entity.getBusinessStatus().equals(BalanceBusinessStatusConstant.ACCEPT) ||
                entity.getBusinessStatus().equals(BalanceBusinessStatusConstant.REJECT)){
                entity.setReceiveTime(DateUtils.getCurrentDateTime());
            }

            entity.setUpdateUser(dto.getLoginUser());
            int affectRow = myMapper.updateBusinessStatus(entity);
            return UnifiedResponseManager.buildSubmitSuccessResponse(affectRow);
        } catch (Exception ex) {
            logger.error(ex.toString());
            return UnifiedResponseManager.buildExceptionResponse();
        }
    }

    @Override
    public UnifiedResponse completeBusiness(BusinessFlowDTO dto) {
        try {
            BusinessFlowEntity entity = new BusinessFlowEntity();
            ObjectConvertUtils.toBean(dto, entity);
            entity.setBusinessStatus(BalanceBusinessStatusConstant.COMPLETE);
            entity.setCompleteTime(DateUtils.getCurrentDateTime());
            entity.setUpdateUser(dto.getLoginUser());
            int affectRow = myMapper.completeBusiness(entity);
            return UnifiedResponseManager.buildSubmitSuccessResponse(affectRow);
        } catch (Exception ex) {
            logger.error(ex.toString());
            return UnifiedResponseManager.buildExceptionResponse();
        }
    }

    @Override
    public UnifiedResponse addCallBack(BusinessFlowDTO dto) {
        try {
            BusinessFlowEntity entity = new BusinessFlowEntity();
            ObjectConvertUtils.toBean(dto, entity);
            entity.setUpdateUser(dto.getLoginUser());
            int affectRow = myMapper.updateCallBack(entity);
            return UnifiedResponseManager.buildSubmitSuccessResponse(affectRow);
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
    public UnifiedResponse add(BusinessFlowDTO dto) {
        try {
            BusinessFlowEntity entity = new BusinessFlowEntity();
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
    public UnifiedResponse change(BusinessFlowDTO dto) {
        return null;
    }

    @Override
    public UnifiedResponse changeDataStatus(BusinessFlowDTO dto) {
        return null;
    }

    @Override
    public UnifiedResponse delete(int id) {
        return null;
    }
}

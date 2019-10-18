package com.johnny.bankworker.service.impl.workerbalance;

import com.johnny.bankworker.common.ObjectConvertUtils;
import com.johnny.bankworker.constant.ResponseDataConstant;
import com.johnny.bankworker.dto.ClockInfoDTO;
import com.johnny.bankworker.entity.ClockInfoEntity;
import com.johnny.bankworker.manager.UnifiedResponseManager;
import com.johnny.bankworker.mapper.workerbalance.ClockInfoMapper;
import com.johnny.bankworker.service.workerbalance.ClockInfoService;
import com.johnny.bankworker.vo.ClockInfoVO;
import com.johnny.bankworker.vo.UnifiedResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClockInfoServiceImpl implements ClockInfoService {
    @Autowired
    private ClockInfoMapper myMapper;
    private Logger logger = LogManager.getLogger(ClockInfoServiceImpl.class);
    @Override
    public UnifiedResponse findCurrentClockInfo(String bankCode, String branchCode, int staffID) {
        try {
            ClockInfoEntity entity =  myMapper.searchCurrentClockInfo(bankCode, branchCode, staffID);
            if(entity == null){
                return UnifiedResponseManager.buildSearchSuccessResponse(ResponseDataConstant.NO_SEARCH_COUNT, ResponseDataConstant.NO_DATA);
            }
            ClockInfoVO model = new ClockInfoVO();
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
    public UnifiedResponse add(ClockInfoDTO dto) {
        try {
            ClockInfoEntity entity = new ClockInfoEntity();
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
    public UnifiedResponse change(ClockInfoDTO dto) {
        return null;
    }

    @Override
    public UnifiedResponse changeDataStatus(ClockInfoDTO dto) {
        return null;
    }

    @Override
    public UnifiedResponse delete(int id) {
        return null;
    }
}

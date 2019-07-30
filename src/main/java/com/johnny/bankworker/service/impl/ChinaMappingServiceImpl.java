package com.johnny.bankworker.service.impl;

import com.johnny.bankworker.common.ObjectConvertUtils;
import com.johnny.bankworker.constant.ResponseDataConstant;
import com.johnny.bankworker.entity.ChinaMappingEntity;
import com.johnny.bankworker.manager.UnifiedResponseManager;
import com.johnny.bankworker.mapper.ChinaMappingMapper;
import com.johnny.bankworker.service.ChinaMappingService;
import com.johnny.bankworker.vo.ChinaMappingVO;
import com.johnny.bankworker.vo.UnifiedResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ChinaMappingServiceImpl implements ChinaMappingService {
    @Autowired
    private ChinaMappingMapper myMapper;
    private Logger logger = LogManager.getLogger(ChinaMappingServiceImpl.class);
    @Override
    public UnifiedResponse searchByParentCode(int parentCode) {
        try {
            List<ChinaMappingEntity> entityList =  myMapper.searchByParentCode(parentCode);
            if(entityList == null || entityList.size() == 0){
                return UnifiedResponseManager.buildSearchSuccessResponse(ResponseDataConstant.NO_SEARCH_COUNT, ResponseDataConstant.NO_DATA);
            }

            List<ChinaMappingVO> modelList = new ArrayList<>();
            for (ChinaMappingEntity entity : entityList) {
                ChinaMappingVO model = new ChinaMappingVO();
                ObjectConvertUtils.toBean(entity, model);
                modelList.add(model);
            }
            return UnifiedResponseManager.buildSearchSuccessResponse(modelList.size(), modelList);
        } catch (Exception ex) {
            logger.error(ex.toString());
            return UnifiedResponseManager.buildExceptionResponse();
        }
    }
}

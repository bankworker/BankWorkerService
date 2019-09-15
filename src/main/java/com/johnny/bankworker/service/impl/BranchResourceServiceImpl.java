package com.johnny.bankworker.service.impl;

import com.johnny.bankworker.common.ObjectConvertUtils;
import com.johnny.bankworker.constant.ResponseDataConstant;
import com.johnny.bankworker.dto.BranchResourceDTO;
import com.johnny.bankworker.entity.BranchResourceEntity;
import com.johnny.bankworker.manager.UnifiedResponseManager;
import com.johnny.bankworker.mapper.BranchResourceMapper;
import com.johnny.bankworker.service.BranchResourceService;
import com.johnny.bankworker.vo.BranchResourceVO;
import com.johnny.bankworker.vo.UnifiedResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BranchResourceServiceImpl implements BranchResourceService {

    @Autowired
    private BranchResourceMapper myMapper;
    private Logger logger = LogManager.getLogger(BranchResourceServiceImpl.class);

    @Override
    public UnifiedResponse findList4Branch(int pageNumber, int pageSize, String bankCode, String branchCode) {
        try {
            int startIndex = (pageNumber - 1) * pageSize;
            List<BranchResourceVO> modelList = new ArrayList<>();
            int totalCount = myMapper.searchTotalCount4Branch(bankCode, branchCode);
            if(totalCount == 0){
                return UnifiedResponseManager.buildSearchSuccessResponse(ResponseDataConstant.NO_SEARCH_COUNT, ResponseDataConstant.NO_DATA);
            }
            List<BranchResourceEntity> entityList =  myMapper.searchList4Branch(startIndex, pageSize, bankCode, branchCode);
            for (BranchResourceEntity entity : entityList) {
                BranchResourceVO model = new BranchResourceVO();
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
    public UnifiedResponse add(BranchResourceDTO dto) {
        try {
            int affectRow = 0;
            String[] resourceUrlList = dto.getResourceUrlList().split(",");
            for (String resourceUrl : resourceUrlList) {
                BranchResourceEntity branchNewsEntity = new BranchResourceEntity();
                ObjectConvertUtils.toBean(dto, branchNewsEntity);
                branchNewsEntity.setResourceUrl(resourceUrl);
                branchNewsEntity.setCreateUser(dto.getLoginUser());
                branchNewsEntity.setUpdateUser(dto.getLoginUser());
                affectRow += myMapper.insert(branchNewsEntity);
            }
            return UnifiedResponseManager.buildSubmitSuccessResponse(affectRow);
        } catch (Exception ex) {
            logger.error(ex.toString());
            return UnifiedResponseManager.buildExceptionResponse();
        }
    }

    @Override
    public UnifiedResponse change(BranchResourceDTO dto) {
        try {
            BranchResourceEntity branchNewsEntity = new BranchResourceEntity();
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
    public UnifiedResponse delete(int id) {
        try {
            int affectRow = myMapper.delete(id);
            return UnifiedResponseManager.buildSubmitSuccessResponse(affectRow);
        } catch (Exception ex) {
            logger.error(ex.toString());
            return UnifiedResponseManager.buildExceptionResponse();
        }
    }

    @Override
    public UnifiedResponse changeDataStatus(BranchResourceDTO dto) {
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
}

package com.johnny.bankworker.service.impl;

import com.johnny.bankworker.common.ObjectConvertUtils;
import com.johnny.bankworker.constant.ResponseDataConstant;
import com.johnny.bankworker.dto.BranchNewsDTO;
import com.johnny.bankworker.entity.BranchNewsEntity;
import com.johnny.bankworker.manager.UnifiedResponseManager;
import com.johnny.bankworker.mapper.BranchNewsMapper;
import com.johnny.bankworker.service.BranchNewsService;
import com.johnny.bankworker.vo.BranchNewsVO;
import com.johnny.bankworker.vo.UnifiedResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BranchNewsServiceImpl implements BranchNewsService {
    @Autowired
    private BranchNewsMapper branchNewsMapper;
    private Logger logger = LogManager.getLogger(BranchNewsServiceImpl.class);

    @Override
    public UnifiedResponse findList4Branch(int pageNumber, int pageSize, String bankCode, String branchCode) {
        try {
            int startIndex = (pageNumber - 1) * pageSize;
            List<BranchNewsVO> modelList = new ArrayList<>();
            int totalCount = branchNewsMapper.searchTotalCount4Branch(bankCode, branchCode);
            if(totalCount == 0){
                return UnifiedResponseManager.buildSearchSuccessResponse(ResponseDataConstant.NO_SEARCH_COUNT, ResponseDataConstant.NO_DATA);
            }
            List<BranchNewsEntity> entityList =  branchNewsMapper.searchList4Branch(startIndex, pageSize, bankCode, branchCode);
            for (BranchNewsEntity entity : entityList) {
                BranchNewsVO model = new BranchNewsVO();
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
    public UnifiedResponse findList(int pageNumber, int pageSize, String dataStatus) {
        return null;
    }

    @Override
    public UnifiedResponse find(int id, String dataStatus) {
        return null;
    }

    @Override
    public UnifiedResponse find(int newsID) {
        try {
            BranchNewsVO model = new BranchNewsVO();
            BranchNewsEntity entity =  branchNewsMapper.searchByNewsID(newsID);
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
    public UnifiedResponse existCheck(String value) {
        return null;
    }

    @Override
    public UnifiedResponse add(BranchNewsDTO dto) {
        try {
            BranchNewsEntity branchNewsEntity = new BranchNewsEntity();
            ObjectConvertUtils.toBean(dto, branchNewsEntity);
            branchNewsEntity.setCreateUser(dto.getLoginUser());
            branchNewsEntity.setUpdateUser(dto.getLoginUser());
            int affectRow = branchNewsMapper.insert(branchNewsEntity);
            return UnifiedResponseManager.buildSubmitSuccessResponse(affectRow);
        } catch (Exception ex) {
            logger.error(ex.toString());
            return UnifiedResponseManager.buildExceptionResponse();
        }
    }

    @Override
    public UnifiedResponse change(BranchNewsDTO dto) {
        try {
            BranchNewsEntity branchNewsEntity = new BranchNewsEntity();
            ObjectConvertUtils.toBean(dto, branchNewsEntity);
            branchNewsEntity.setUpdateUser(dto.getLoginUser());
            int affectRow = branchNewsMapper.update(branchNewsEntity);
            return UnifiedResponseManager.buildSubmitSuccessResponse(affectRow);
        } catch (Exception ex) {
            logger.error(ex.toString());
            return UnifiedResponseManager.buildExceptionResponse();
        }
    }

    @Override
    public UnifiedResponse changeDataStatus(BranchNewsDTO dto) {
        return null;
    }

    @Override
    public UnifiedResponse delete(int id) {
        try {
            int affectRow = branchNewsMapper.delete(id);
            return UnifiedResponseManager.buildSubmitSuccessResponse(affectRow);
        } catch (Exception ex) {
            logger.error(ex.toString());
            return UnifiedResponseManager.buildExceptionResponse();
        }
    }
}

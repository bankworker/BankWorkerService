package com.johnny.bankworker.service.impl;

import com.johnny.bankworker.common.ObjectConvertUtils;
import com.johnny.bankworker.constant.ResponseDataConstant;
import com.johnny.bankworker.dto.BranchStaffPostDTO;
import com.johnny.bankworker.entity.BranchStaffPostEntity;
import com.johnny.bankworker.manager.UnifiedResponseManager;
import com.johnny.bankworker.mapper.BranchStaffPostMapper;
import com.johnny.bankworker.service.BranchStaffPostService;
import com.johnny.bankworker.vo.BranchStaffPostVO;
import com.johnny.bankworker.vo.UnifiedResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BranchStaffPostServiceImpl implements BranchStaffPostService {
    @Autowired
    private BranchStaffPostMapper myMapper;
    private Logger logger = LogManager.getLogger(BranchStaffPostServiceImpl.class);

    @Override
    public UnifiedResponse findList(int pageNumber, int pageSize, String bankCode, String branchCode) {
        try {
            int startIndex = (pageNumber - 1) * pageSize;
            List<BranchStaffPostVO> modelList = new ArrayList<>();
            int totalCount =  myMapper.searchTotalCount4Branch(bankCode, branchCode);
            if(totalCount == 0){
                return UnifiedResponseManager.buildSearchSuccessResponse(ResponseDataConstant.NO_SEARCH_COUNT, ResponseDataConstant.NO_DATA);
            }
            List<BranchStaffPostEntity> entityList = myMapper.searchList4Branch(startIndex, pageSize, bankCode, branchCode);
            for (BranchStaffPostEntity entity : entityList) {
                BranchStaffPostVO model = new BranchStaffPostVO();
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
    public UnifiedResponse existCheck(String bankCode, String branchCode, String staffPostName) {
        try {
            int count =  myMapper.existCheck4Branch(bankCode, branchCode, staffPostName);
            return UnifiedResponseManager.buildSearchSuccessResponse(count, count);
        } catch (Exception ex) {
            logger.error(ex.toString());
            return UnifiedResponseManager.buildExceptionResponse();
        }
    }

    @Override
    public UnifiedResponse checkStaffPostIsUsing(String bankCode, String branchCode, int staffPostID) {
        try {
            int count =  myMapper.searchStaffPostIsUsingCount(bankCode, branchCode, staffPostID);
            boolean isUsing = count > 0;
            return UnifiedResponseManager.buildSearchSuccessResponse(count, isUsing);
        } catch (Exception ex) {
            logger.error(ex.toString());
            return UnifiedResponseManager.buildExceptionResponse();
        }
    }

    @Override
    public UnifiedResponse find(int id, String dataStatus) {
        try {
            BranchStaffPostEntity entity =  myMapper.searchByID(id, dataStatus);
            if(entity == null){
                return UnifiedResponseManager.buildSearchSuccessResponse(ResponseDataConstant.NO_SEARCH_COUNT, ResponseDataConstant.NO_DATA);
            }
            BranchStaffPostVO model = new BranchStaffPostVO();
            ObjectConvertUtils.toBean(entity, model);
            return UnifiedResponseManager.buildSearchSuccessResponse(1, model);
        } catch (Exception ex) {
            logger.error(ex.toString());
            return UnifiedResponseManager.buildExceptionResponse();
        }
    }

    @Override
    public UnifiedResponse add(BranchStaffPostDTO dto) {
        try {
            BranchStaffPostEntity branchNewsEntity = new BranchStaffPostEntity();
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
    public UnifiedResponse change(BranchStaffPostDTO dto) {
        try {
            BranchStaffPostEntity branchNewsEntity = new BranchStaffPostEntity();
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
    public UnifiedResponse changeDataStatus(BranchStaffPostDTO dto) {
        return null;
    }

    @Override
    public UnifiedResponse findList(int pageNumber, int pageSize, String dataStatus) {
        return null;
    }

    @Override
    public UnifiedResponse existCheck(String value) {
        return null;
    }
}

package com.johnny.bankworker.service.impl;

import com.johnny.bankworker.common.ObjectConvertUtils;
import com.johnny.bankworker.constant.DataStatusConstant;
import com.johnny.bankworker.constant.ResponseDataConstant;
import com.johnny.bankworker.dto.FileUploadServiceSettingDTO;
import com.johnny.bankworker.entity.FileUploadServiceSettingEntity;
import com.johnny.bankworker.manager.UnifiedResponseManager;
import com.johnny.bankworker.mapper.FileUploadServiceSettingMapper;
import com.johnny.bankworker.service.FileUploadSettingService;
import com.johnny.bankworker.vo.FileUploadServiceSettingVO;
import com.johnny.bankworker.vo.FileUploadUrlVO;
import com.johnny.bankworker.vo.UnifiedResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FileUploadSettingServiceImpl implements FileUploadSettingService {
    @Autowired
    private FileUploadServiceSettingMapper myMapper;
    private Logger logger = LogManager.getLogger(FileUploadSettingServiceImpl.class);

    @Override
    public UnifiedResponse findList(int pageNumber, int pageSize, String dataStatus) {
        try {
            int startIndex = (pageNumber - 1) * pageSize;
            List<FileUploadServiceSettingVO> modelList = new ArrayList<>();
            int totalCount = myMapper.searchTotalCount(dataStatus.equals(DataStatusConstant.All) ? null : dataStatus);
            if(totalCount == 0){
                return UnifiedResponseManager.buildSearchSuccessResponse(ResponseDataConstant.NO_SEARCH_COUNT, ResponseDataConstant.NO_DATA);
            }
            List<FileUploadServiceSettingEntity> entityList =  myMapper.searchList(startIndex, pageSize, dataStatus.equals(DataStatusConstant.All) ? null : dataStatus);
            for (FileUploadServiceSettingEntity entity : entityList) {
                FileUploadServiceSettingVO model = new FileUploadServiceSettingVO();
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
    public UnifiedResponse findList4Branch(int pageNumber, int pageSize, String bankCode, String branchCode) {
        try {
            String serverBankCode = "0";
            String serverBranchCode = "0";
            int startIndex = (pageNumber - 1) * pageSize;
            FileUploadUrlVO model = new FileUploadUrlVO();
            List<FileUploadServiceSettingEntity> entityList4Server =  myMapper.searchList4Branch(startIndex, pageSize, serverBankCode, serverBranchCode);
            List<FileUploadServiceSettingEntity> entityList4Customer =  myMapper.searchList4Branch(startIndex, pageSize, bankCode, branchCode);
            if(entityList4Server.isEmpty()){
                return UnifiedResponseManager.buildSearchSuccessResponse(ResponseDataConstant.NO_SEARCH_COUNT, ResponseDataConstant.NO_DATA);
            }

            model.setBankCode(bankCode);
            model.setBranchCode(branchCode);
            model.setServerFileUploadUrl(entityList4Server.get(0).getServiceUrl());
            if(!entityList4Customer.isEmpty()){
                model.setCustomerFileUploadUrl(entityList4Customer.get(0).getServiceUrl());
            }

            return UnifiedResponseManager.buildSearchSuccessResponse(ResponseDataConstant.ONE_SEARCH_COUNT, model);
        } catch (Exception ex) {
            logger.error(ex.toString());
            return UnifiedResponseManager.buildExceptionResponse();
        }
    }

    @Override
    public UnifiedResponse find(int id, String dataStatus) {
        return null;
    }

    @Override
    public UnifiedResponse existCheck(String value) {
        try {
            int count =  myMapper.existCheck(value);
            return UnifiedResponseManager.buildSearchSuccessResponse(count, count);
        } catch (Exception ex) {
            logger.error(ex.toString());
            return UnifiedResponseManager.buildExceptionResponse();
        }
    }

    @Override
    public UnifiedResponse add(FileUploadServiceSettingDTO dto) {
        try {
            FileUploadServiceSettingEntity entity = new FileUploadServiceSettingEntity();
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
    public UnifiedResponse change(FileUploadServiceSettingDTO dto) {
        try {
            FileUploadServiceSettingEntity entity = new FileUploadServiceSettingEntity();
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
    public UnifiedResponse changeDataStatus(FileUploadServiceSettingDTO dto) {
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

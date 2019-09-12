package com.johnny.bankworker.service.impl;

import com.johnny.bankworker.common.ObjectConvertUtils;
import com.johnny.bankworker.constant.DataStatusConstant;
import com.johnny.bankworker.constant.ResponseDataConstant;
import com.johnny.bankworker.dto.BankBranchStaffAccount4OriginalDTO;
import com.johnny.bankworker.dto.BankBranchStaffAccountDTO;
import com.johnny.bankworker.entity.BankBranchStaffAccountEntity;
import com.johnny.bankworker.manager.UnifiedResponseManager;
import com.johnny.bankworker.mapper.BankBranchStaffAccountMapper;
import com.johnny.bankworker.service.BankBranchStaffAccountService;
import com.johnny.bankworker.service.BaseService;
import com.johnny.bankworker.vo.BankBranchStaffAccountVO;
import com.johnny.bankworker.vo.UnifiedResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BankBranchStaffAccountServiceImpl implements BankBranchStaffAccountService {

    @Autowired
    private BankBranchStaffAccountMapper myMapper;
    private Logger logger = LogManager.getLogger(BankBranchStaffAccountServiceImpl.class);

    @Override
    public UnifiedResponse findList(int pageNumber, int pageSize, String dataStatus) {
        try {
            int startIndex = (pageNumber - 1) * pageSize;
            List<BankBranchStaffAccountVO> modelList = new ArrayList<>();
            int totalCount = myMapper.searchTotalCount(dataStatus.equals(DataStatusConstant.All) ? null : dataStatus);
            if(totalCount == 0){
                return UnifiedResponseManager.buildSearchSuccessResponse(ResponseDataConstant.NO_SEARCH_COUNT, ResponseDataConstant.NO_DATA);
            }
            List<BankBranchStaffAccountEntity> entityList =  myMapper.searchList(startIndex, pageSize, dataStatus.equals(DataStatusConstant.All) ? null : dataStatus);
            for (BankBranchStaffAccountEntity entity : entityList) {
                BankBranchStaffAccountVO model = new BankBranchStaffAccountVO();
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
    public UnifiedResponse find(int id, String dataStatus) {
        try {
            BankBranchStaffAccountEntity entity =  myMapper.searchByID(id, dataStatus);
            if(entity == null){
                return UnifiedResponseManager.buildSearchSuccessResponse(ResponseDataConstant.NO_SEARCH_COUNT, ResponseDataConstant.NO_DATA);
            }
            BankBranchStaffAccountVO model = new BankBranchStaffAccountVO();
            ObjectConvertUtils.toBean(entity, model);
            return UnifiedResponseManager.buildSearchSuccessResponse(1, model);
        } catch (Exception ex) {
            logger.error(ex.toString());
            return UnifiedResponseManager.buildExceptionResponse();
        }
    }

    @Override
    public UnifiedResponse findAuthorizedSystem(int accountID) {
        try {
            List<BankBranchStaffAccountVO> modelList = new ArrayList<>();
            BankBranchStaffAccountEntity accountEntity =  myMapper.searchByID(accountID, DataStatusConstant.Normal);
            if(accountEntity == null){
                return UnifiedResponseManager.buildSearchSuccessResponse(ResponseDataConstant.NO_SEARCH_COUNT, ResponseDataConstant.NO_DATA);
            }

            List<BankBranchStaffAccountEntity> authorizedSystemEntityList = myMapper.searchAuthorizedSystem(accountEntity.getAccount(),accountEntity.getPassword());
            if(authorizedSystemEntityList.isEmpty()){
                return UnifiedResponseManager.buildSearchSuccessResponse(ResponseDataConstant.NO_SEARCH_COUNT, ResponseDataConstant.NO_DATA);
            }

            for (BankBranchStaffAccountEntity entity : authorizedSystemEntityList) {
                BankBranchStaffAccountVO model = new BankBranchStaffAccountVO();
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
    public UnifiedResponse login(String account, String password, int systemID) {
        try {
            BankBranchStaffAccountVO model = new BankBranchStaffAccountVO();
            BankBranchStaffAccountEntity entity =  myMapper.login(account, password, systemID);
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
    public UnifiedResponse add(BankBranchStaffAccount4OriginalDTO dto) {
        try {
            int affectRow = 0;
            BankBranchStaffAccountEntity entity = new BankBranchStaffAccountEntity();
            String[] multipleID = dto.getSystemMultipleID().split(",");
            if(multipleID.length > 1){
                for (String systemID : multipleID) {
                    BankBranchStaffAccountDTO branchStaffAccountDTO = new BankBranchStaffAccountDTO();
                    ObjectConvertUtils.toBean(dto, branchStaffAccountDTO);
                    branchStaffAccountDTO.setSystemID(Integer.parseInt(systemID));
                    ObjectConvertUtils.toBean(branchStaffAccountDTO, entity);
                    entity.setCreateUser(branchStaffAccountDTO.getLoginUser());
                    entity.setUpdateUser(branchStaffAccountDTO.getLoginUser());
                    affectRow += myMapper.insert(entity);
                }
            }else{
                BankBranchStaffAccountDTO branchStaffAccountDTO = new BankBranchStaffAccountDTO();
                ObjectConvertUtils.toBean(dto, branchStaffAccountDTO);
                branchStaffAccountDTO.setSystemID(Integer.parseInt(dto.getSystemMultipleID()));
                ObjectConvertUtils.toBean(branchStaffAccountDTO, entity);
                entity.setCreateUser(branchStaffAccountDTO.getLoginUser());
                entity.setUpdateUser(branchStaffAccountDTO.getLoginUser());
                affectRow = myMapper.insert(entity);
            }


            return UnifiedResponseManager.buildSubmitSuccessResponse(affectRow);
        } catch (Exception ex) {
            logger.error(ex.toString());
            return UnifiedResponseManager.buildExceptionResponse();
        }
    }

    @Override
    public UnifiedResponse change(BankBranchStaffAccount4OriginalDTO dto) {
        try {
            BankBranchStaffAccountEntity entity = new BankBranchStaffAccountEntity();
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
    public UnifiedResponse changeDataStatus(BankBranchStaffAccount4OriginalDTO dto) {
        try {
            BankBranchStaffAccountEntity entity = new BankBranchStaffAccountEntity();
            ObjectConvertUtils.toBean(dto, entity);
            entity.setUpdateUser(dto.getLoginUser());
            if(dto.getDataStatus() == null){
                entity.setDataStatus(DataStatusConstant.Normal);
            }
            int affectRow = myMapper.updateDataStatus(entity);
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
}

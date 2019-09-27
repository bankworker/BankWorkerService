package com.johnny.bankworker.service.impl;

import com.johnny.bankworker.common.ObjectConvertUtils;
import com.johnny.bankworker.constant.DataStatusConstant;
import com.johnny.bankworker.constant.ResponseDataConstant;
import com.johnny.bankworker.dto.BankBranchStaffDTO;
import com.johnny.bankworker.entity.BankBranchStaffAccountEntity;
import com.johnny.bankworker.entity.BankBranchStaffEntity;
import com.johnny.bankworker.manager.UnifiedResponseManager;
import com.johnny.bankworker.mapper.BankBranchStaffAccountMapper;
import com.johnny.bankworker.mapper.BankBranchStaffMapper;
import com.johnny.bankworker.service.BankBranchStaffService;
import com.johnny.bankworker.vo.BankBranchStaffVO;
import com.johnny.bankworker.vo.UnifiedResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BankBranchStaffServiceImpl implements BankBranchStaffService {
    @Autowired
    private BankBranchStaffMapper myMapper;

    @Autowired
    private BankBranchStaffAccountMapper accountMapper;

    private Logger logger = LogManager.getLogger(BankBranchStaffServiceImpl.class);

    @Override
    public UnifiedResponse findList4Branch(int pageNumber, int pageSize, String bankCode, String branchCode) {
        try {
            int startIndex = (pageNumber - 1) * pageSize;
            List<BankBranchStaffVO> modelList = new ArrayList<>();
            int totalCount =  myMapper.searchTotalCount4Branch(bankCode, branchCode);
            if(totalCount == 0){
                return UnifiedResponseManager.buildSearchSuccessResponse(ResponseDataConstant.NO_SEARCH_COUNT, ResponseDataConstant.NO_DATA);
            }
            List<BankBranchStaffEntity> entityList = myMapper.searchList4Branch(startIndex, pageSize, bankCode, branchCode);
            for (BankBranchStaffEntity entity : entityList) {
                BankBranchStaffVO model = new BankBranchStaffVO();
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
        try {
            BankBranchStaffEntity entity =  myMapper.searchByID(id, dataStatus);
            if(entity == null){
                return UnifiedResponseManager.buildSearchSuccessResponse(ResponseDataConstant.NO_SEARCH_COUNT, ResponseDataConstant.NO_DATA);
            }
            BankBranchStaffVO model = new BankBranchStaffVO();
            ObjectConvertUtils.toBean(entity, model);
            return UnifiedResponseManager.buildSearchSuccessResponse(1, model);
        } catch (Exception ex) {
            logger.error(ex.toString());
            return UnifiedResponseManager.buildExceptionResponse();
        }
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
    public UnifiedResponse add(BankBranchStaffDTO dto) {
        try {
            BankBranchStaffEntity entity = new BankBranchStaffEntity();
            ObjectConvertUtils.toBean(dto, entity);
            entity.setCreateUser(dto.getLoginUser());
            entity.setUpdateUser(dto.getLoginUser());
            int affectRow = myMapper.insert(entity);

            BankBranchStaffAccountEntity accountEntity = new BankBranchStaffAccountEntity();
            accountEntity.setBankCode(dto.getBankCode());
            accountEntity.setBranchCode(dto.getBranchCode());
            accountEntity.setSystemID(2);
            accountEntity.setAccount(dto.getStaffCellphone());
            accountEntity.setPassword("111111");
            accountEntity.setCreateUser(dto.getLoginUser());
            accountEntity.setUpdateUser(dto.getLoginUser());
            affectRow += accountMapper.insert(accountEntity);

            return UnifiedResponseManager.buildSubmitSuccessResponse(affectRow);
        } catch (Exception ex) {
            logger.error(ex.toString());
            return UnifiedResponseManager.buildExceptionResponse();
        }
    }

    @Override
    public UnifiedResponse change(BankBranchStaffDTO dto) {
        try {
            BankBranchStaffEntity entity = new BankBranchStaffEntity();
            BankBranchStaffEntity entityOriginal =  myMapper.searchByID(dto.getStaffID(), DataStatusConstant.Normal);
            if(entityOriginal == null){
                return UnifiedResponseManager.buildSubmitSuccessResponse(ResponseDataConstant.NO_SEARCH_COUNT);
            }

            ObjectConvertUtils.toBean(dto, entity);
            entity.setUpdateUser(dto.getLoginUser());
            int affectRow = myMapper.update(entity);

            BankBranchStaffAccountEntity accountEntity = new BankBranchStaffAccountEntity();
            accountEntity.setBankCode(dto.getBankCode());
            accountEntity.setBranchCode(dto.getBranchCode());
            accountEntity.setSystemID(2);
            accountEntity.setAccountOriginal(entityOriginal.getStaffCellphone());
            accountEntity.setAccount(dto.getStaffCellphone());
            accountEntity.setCreateUser(dto.getLoginUser());
            accountEntity.setUpdateUser(dto.getLoginUser());
            affectRow += accountMapper.updateByAccount(accountEntity);

            return UnifiedResponseManager.buildSubmitSuccessResponse(affectRow);
        } catch (Exception ex) {
            logger.error(ex.toString());
            return UnifiedResponseManager.buildExceptionResponse();
        }
    }

    @Override
    public UnifiedResponse changeDataStatus(BankBranchStaffDTO dto) {
        return null;
    }

    @Override
    public UnifiedResponse delete(int id) {
        try {
            BankBranchStaffEntity entity =  myMapper.searchByID(id, DataStatusConstant.Normal);
            if(entity == null){
                return UnifiedResponseManager.buildSubmitSuccessResponse(ResponseDataConstant.NO_SEARCH_COUNT);
            }

            BankBranchStaffAccountEntity accountEntity = new BankBranchStaffAccountEntity();
            accountEntity.setBankCode(entity.getBankCode());
            accountEntity.setBranchCode(entity.getBranchCode());
            accountEntity.setSystemID(2);
            accountEntity.setAccount(entity.getStaffCellphone());
            accountEntity.setCreateUser(entity.getCreateUser());
            accountEntity.setUpdateUser(entity.getUpdateUser());
            int affectRow = myMapper.delete(id);
            affectRow += accountMapper.deleteByAccount(accountEntity);
            return UnifiedResponseManager.buildSubmitSuccessResponse(affectRow);
        } catch (Exception ex) {
            logger.error(ex.toString());
            return UnifiedResponseManager.buildExceptionResponse();
        }
    }
}

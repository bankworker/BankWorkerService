package com.johnny.bankworker.service.impl;

import com.johnny.bankworker.common.ObjectConvertUtils;
import com.johnny.bankworker.constant.ResponseDataConstant;
import com.johnny.bankworker.dto.BranchArchiveDTO;
import com.johnny.bankworker.entity.BranchArchiveEntity;
import com.johnny.bankworker.manager.UnifiedResponseManager;
import com.johnny.bankworker.mapper.BranchArchiveMapper;
import com.johnny.bankworker.service.BranchArchiveService;
import com.johnny.bankworker.vo.BranchArchiveVO;
import com.johnny.bankworker.vo.UnifiedResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BranchArchiveServiceImpl implements BranchArchiveService {
    @Autowired
    private BranchArchiveMapper myMapper;
    private Logger logger = LogManager.getLogger(BranchArchiveServiceImpl.class);
    /**
     * 查询指定支行的档案内容
     * @param pageNumber 页码
     * @param pageSize   每页数量
     * @param bankCode   银行编码
     * @param branchCode 支行编码
     * @return 返回支行档案内容
     */
    @Override
    public UnifiedResponse findList(int pageNumber, int pageSize, String bankCode, String branchCode) {
        try {
            int startIndex = (pageNumber - 1) * pageSize;
            List<BranchArchiveVO> modelList = new ArrayList<>();
            int totalCount = myMapper.searchTotalCount4Branch(bankCode, branchCode);
            if(totalCount == 0){
                return UnifiedResponseManager.buildSearchSuccessResponse(ResponseDataConstant.NO_SEARCH_COUNT, ResponseDataConstant.NO_DATA);
            }
            List<BranchArchiveEntity> entityList =  myMapper.searchList4Branch(startIndex, pageSize, bankCode, branchCode);
            for (BranchArchiveEntity entity : entityList) {
                BranchArchiveVO model = new BranchArchiveVO();
                ObjectConvertUtils.toBean(entity, model);
                modelList.add(model);
            }
            return UnifiedResponseManager.buildSearchSuccessResponse(totalCount, modelList);
        } catch (Exception ex) {
            logger.error(ex.toString());
            return UnifiedResponseManager.buildExceptionResponse();
        }
    }

    /**
     * 校验指定支行中，某个档案是否已经存在
     * @param bankCode    银行编码
     * @param branchCode  支行编码
     * @param archiveName 档案名称
     * @return 返回是否存在的结果
     */
    @Override
    public UnifiedResponse existCheck(String bankCode, String branchCode, String archiveName) {
        try {
            int count =  myMapper.existCheck(bankCode, branchCode, archiveName);
            boolean isExist = count > 0;
            return UnifiedResponseManager.buildSearchSuccessResponse(count, isExist);
        } catch (Exception ex) {
            logger.error(ex.toString());
            return UnifiedResponseManager.buildExceptionResponse();
        }
    }

    /**
     * 校验指定支行中，某个档案下是否存在子档案
     * @param bankCode        银行编码
     * @param branchCode      支行编码
     * @param archiveParentID 父层级档案编号
     * @return 返回是否存在的结果
     */
    @Override
    public UnifiedResponse existCheck(String bankCode, String branchCode, int archiveParentID) {
        try {
            List<BranchArchiveEntity> entityList =  myMapper.searchByParentArchive(bankCode, branchCode, archiveParentID);
            int childArchivesCount = entityList.isEmpty() ? 0 : entityList.size();
            boolean isExist = childArchivesCount > 0;
            return UnifiedResponseManager.buildSearchSuccessResponse(childArchivesCount, isExist);
        } catch (Exception ex) {
            logger.error(ex.toString());
            return UnifiedResponseManager.buildExceptionResponse();
        }
    }

    /**
     * 修改指定支行档案内容的显示顺序
     * @param dto 档案内容
     * @return 返回更新后的结果
     */
    @Override
    public UnifiedResponse changePosition(BranchArchiveDTO dto) {
        try {
            BranchArchiveEntity entity = new BranchArchiveEntity();
            ObjectConvertUtils.toBean(dto, entity);
            entity.setUpdateUser(dto.getLoginUser());
            int affectRow = myMapper.updatePosition(entity);
            return UnifiedResponseManager.buildSubmitSuccessResponse(affectRow);
        } catch (Exception ex) {
            logger.error(ex.toString());
            return UnifiedResponseManager.buildExceptionResponse();
        }
    }

    /**
     * 修改指定支行档案内容的显示顺序
     * @param dto 档案内容
     * @return 返回更新后的结果
     */
    @Override
    public UnifiedResponse changeOrder(BranchArchiveDTO dto) {
        try {
            BranchArchiveEntity currentEntity = myMapper.searchByArchiveID(dto.getBankCode(), dto.getBranchCode(), dto.getArchiveID());
            BranchArchiveEntity swapEntity = myMapper.searchByArchiveID(dto.getBankCode(), dto.getBranchCode(), dto.getArchiveSwapID());
            if(currentEntity == null || swapEntity == null){
                return UnifiedResponseManager.buildSubmitSuccessResponse(ResponseDataConstant.NO_SEARCH_COUNT);
            }
            int affectRow = 0;
            int currentOrder = currentEntity.getArchiveOrder();
            int swapOrder = swapEntity.getArchiveOrder();

            currentEntity.setArchiveOrder(swapOrder);
            currentEntity.setUpdateUser(dto.getLoginUser());
            swapEntity.setArchiveOrder(currentOrder);
            swapEntity.setUpdateUser(dto.getLoginUser());

            affectRow += myMapper.updateOrder(currentEntity);
            affectRow += myMapper.updateOrder(swapEntity);
            return UnifiedResponseManager.buildSubmitSuccessResponse(affectRow);
        } catch (Exception ex) {
            logger.error(ex.toString());
            return UnifiedResponseManager.buildExceptionResponse();
        }
    }


    /**
     * 添加档案
     * @param dto 档案信息
     * @return 返回添加结果
     */
    @Override
    public UnifiedResponse add(BranchArchiveDTO dto) {
        try {
            int currentMaxOrder = myMapper.searchMaxOrder(dto.getBankCode(), dto.getBranchCode(), dto.getArchiveParentID());
            BranchArchiveEntity entity = new BranchArchiveEntity();
            ObjectConvertUtils.toBean(dto, entity);
            entity.setArchiveOrder(currentMaxOrder + 1);
            entity.setCreateUser(dto.getLoginUser());
            entity.setUpdateUser(dto.getLoginUser());
            int affectRow = myMapper.insert(entity);
            return UnifiedResponseManager.buildSubmitSuccessResponse(affectRow);
        } catch (Exception ex) {
            logger.error(ex.toString());
            return UnifiedResponseManager.buildExceptionResponse();
        }
    }

    /**
     * 修改档案
     * @param dto 档案信息
     * @return 返回修改结果
     */
    @Override
    public UnifiedResponse change(BranchArchiveDTO dto) {
        try {
            BranchArchiveEntity entity = new BranchArchiveEntity();
            ObjectConvertUtils.toBean(dto, entity);
            entity.setUpdateUser(dto.getLoginUser());
            int affectRow = myMapper.update(entity);
            return UnifiedResponseManager.buildSubmitSuccessResponse(affectRow);
        } catch (Exception ex) {
            logger.error(ex.toString());
            return UnifiedResponseManager.buildExceptionResponse();
        }
    }

    /**
     * 删除指定支行的指标
     * @param bankCode   银行编码
     * @param branchCode 支行编码
     * @param archiveID  指标编号
     * @return 返回删除后的影响行数
     */
    @Override
    public UnifiedResponse delete(String bankCode, String branchCode, int archiveID) {
        try {
            int affectRow = myMapper.deleteArchive(bankCode, branchCode, archiveID);
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
    public UnifiedResponse changeDataStatus(BranchArchiveDTO dto) {
        return null;
    }

    @Override
    public UnifiedResponse delete(int id) {
        return null;
    }
}

package com.johnny.bankworker.service.impl;

import com.johnny.bankworker.common.ObjectConvertUtils;
import com.johnny.bankworker.constant.ResponseDataConstant;
import com.johnny.bankworker.dto.BranchArchiveDetailDTO;
import com.johnny.bankworker.entity.BranchArchiveDetailEntity;
import com.johnny.bankworker.manager.UnifiedResponseManager;
import com.johnny.bankworker.mapper.BranchArchiveDetailMapper;
import com.johnny.bankworker.service.BranchArchiveDetailService;
import com.johnny.bankworker.vo.BranchArchiveDetailVO;
import com.johnny.bankworker.vo.UnifiedResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BranchArchiveDetailServiceImpl implements BranchArchiveDetailService {
    @Autowired
    private BranchArchiveDetailMapper myMapper;
    private Logger logger = LogManager.getLogger(BranchArchiveDetailServiceImpl.class);

    /**
     * 查询指定支行某个档案的详细内容
     * @param bankCode   银行编码
     * @param branchCode 支行编码
     * @param archiveID  档案编号
     * @return 返回档案的详细内容
     */
    @Override
    public UnifiedResponse findList(String bankCode, String branchCode, int archiveID) {
        try {
            List<BranchArchiveDetailVO> modelList = new ArrayList<>();
            List<BranchArchiveDetailEntity> entityList =  myMapper.searchListOfArchive(bankCode, branchCode, archiveID);
            if(entityList.isEmpty()){
                return UnifiedResponseManager.buildSearchSuccessResponse(ResponseDataConstant.NO_SEARCH_COUNT, ResponseDataConstant.NO_DATA);
            }
            for (BranchArchiveDetailEntity entity : entityList) {
                BranchArchiveDetailVO model = new BranchArchiveDetailVO();
                ObjectConvertUtils.toBean(entity, model);
                modelList.add(model);
            }
            return UnifiedResponseManager.buildSearchSuccessResponse(modelList.size(), modelList);
        } catch (Exception ex) {
            logger.error(ex.toString());
            return UnifiedResponseManager.buildExceptionResponse();
        }
    }

    /**
     * 删除指定支行的某个档案的详细信息
     * @param bankCode   银行编码
     * @param branchCode 支行编码
     * @param archiveID  档案编号
     * @return 返回删除后的结果
     */
    @Override
    public UnifiedResponse deleteOfArchive(String bankCode, String branchCode, int archiveID) {
        try {
            int affectRow = myMapper.deleteOfArchive(bankCode, branchCode, archiveID);
            return UnifiedResponseManager.buildSubmitSuccessResponse(affectRow);
        } catch (Exception ex) {
            logger.error(ex.toString());
            return UnifiedResponseManager.buildExceptionResponse();
        }
    }

    /**
     * 删除指定支行，某个档案中的指定信息
     * @param bankCode        银行编码
     * @param branchCode      支行编码
     * @param archiveDetailID 档案详细编号
     * @return 返回删除后的结果
     */
    @Override
    public UnifiedResponse deleteOfArchiveDetail(String bankCode, String branchCode, int archiveID, int archiveDetailID) {
        try {
            int affectRow = myMapper.deleteOfArchiveDetail(bankCode, branchCode, archiveID, archiveDetailID);
            return UnifiedResponseManager.buildSubmitSuccessResponse(affectRow);
        } catch (Exception ex) {
            logger.error(ex.toString());
            return UnifiedResponseManager.buildExceptionResponse();
        }
    }

    /**
     * 移动档案详细内容的顺序
     * @param dto 档案详细信息
     * @return 返回更新后的结果
     */
    @Override
    public UnifiedResponse changeOrder(BranchArchiveDetailDTO dto) {
        try {
            BranchArchiveDetailEntity currentEntity = myMapper.searchByArchiveDetailID(dto.getBankCode(), dto.getBranchCode(), dto.getArchiveID(), dto.getArchiveDetailID());
            BranchArchiveDetailEntity swapEntity = myMapper.searchByArchiveDetailID(dto.getBankCode(), dto.getBranchCode(), dto.getArchiveID(),dto.getArchiveDetailSwapID());
            if(currentEntity == null || swapEntity == null){
                return UnifiedResponseManager.buildSubmitSuccessResponse(ResponseDataConstant.NO_SEARCH_COUNT);
            }

            int affectRow = 0;
            int currentOrder = currentEntity.getArchiveDetailOrder();
            int swapOrder = swapEntity.getArchiveDetailOrder();

            currentEntity.setArchiveDetailOrder(swapOrder);
            currentEntity.setUpdateUser(dto.getLoginUser());
            swapEntity.setArchiveDetailOrder(currentOrder);
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
     * 添加一条档案的详细信息
     * @param dto 档案详细信息
     * @return 返回添加结果
     */
    @Override
    public UnifiedResponse add(BranchArchiveDetailDTO dto) {
        try {
            int currentMaxOrder = myMapper.searchMaxOrder(dto.getBankCode(), dto.getBranchCode(), dto.getArchiveID(), dto.getArchiveDetailType());
            BranchArchiveDetailEntity entity = new BranchArchiveDetailEntity();
            ObjectConvertUtils.toBean(dto, entity);
            entity.setArchiveDetailOrder(currentMaxOrder + 1);
            entity.setCreateUser(dto.getLoginUser());
            entity.setUpdateUser(dto.getLoginUser());
            int affectRow = myMapper.insert(entity);
            return UnifiedResponseManager.buildSubmitSuccessResponse(affectRow,entity.getArchiveDetailID());
        } catch (Exception ex) {
            logger.error(ex.toString());
            return UnifiedResponseManager.buildExceptionResponse();
        }
    }

    /**
     * 修改一条档案的详细信息
     * @param dto 档案详细信息
     * @return 返回修改结果
     */
    @Override
    public UnifiedResponse change(BranchArchiveDetailDTO dto) {
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

    @Override
    public UnifiedResponse changeDataStatus(BranchArchiveDetailDTO dto) {
        return null;
    }

    @Override
    public UnifiedResponse delete(int id) {
        return null;
    }
}

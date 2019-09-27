package com.johnny.bankworker.service.impl;

import com.johnny.bankworker.common.JsonUtils;
import com.johnny.bankworker.common.ObjectConvertUtils;
import com.johnny.bankworker.constant.ResponseDataConstant;
import com.johnny.bankworker.dto.MediaModuleDTO;
import com.johnny.bankworker.dto.MediaModuleDetailDTO;
import com.johnny.bankworker.entity.MediaModuleDetailEntity;
import com.johnny.bankworker.entity.MediaModuleEntity;
import com.johnny.bankworker.manager.UnifiedResponseManager;
import com.johnny.bankworker.mapper.MediaModuleDetailMapper;
import com.johnny.bankworker.mapper.MediaModuleMapper;
import com.johnny.bankworker.service.BranchMediaModuleService;
import com.johnny.bankworker.vo.MediaModuleDetailVO;
import com.johnny.bankworker.vo.MediaModuleVO;
import com.johnny.bankworker.vo.UnifiedResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BranchMediaModuleServiceImpl implements BranchMediaModuleService {
    @Autowired
    private MediaModuleMapper mediaModuleMapper;
    @Autowired
    private MediaModuleDetailMapper mediaModuleDetailMapper;

    private Logger logger = LogManager.getLogger(BranchMediaModuleServiceImpl.class);

    @Override
    public UnifiedResponse findList4Branch(int pageNumber, int pageSize, String bankCode, String branchCode) {
        try {
            int startIndex = (pageNumber - 1) * pageSize;
            List<MediaModuleVO> modelList = new ArrayList<>();
            int totalCount = mediaModuleMapper.searchTotalCount4Branch(bankCode, branchCode);
            if(totalCount == 0){
                return UnifiedResponseManager.buildSearchSuccessResponse(ResponseDataConstant.NO_SEARCH_COUNT, ResponseDataConstant.NO_DATA);
            }
            List<MediaModuleEntity> entityList =  mediaModuleMapper.searchList4Branch(startIndex, pageSize, bankCode, branchCode);
            for (MediaModuleEntity entity : entityList) {
                MediaModuleVO model = new MediaModuleVO();
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
    public UnifiedResponse find(String bankCode, String branchCode, int mediaModuleID) {
        try {
            MediaModuleVO model = new MediaModuleVO();
            List<MediaModuleDetailVO> moduleDetailList = new ArrayList<>();

            MediaModuleEntity entity =  mediaModuleMapper.searchByModuleID(bankCode, branchCode, mediaModuleID);
            if(entity == null){
                return UnifiedResponseManager.buildSearchSuccessResponse(ResponseDataConstant.NO_SEARCH_COUNT, ResponseDataConstant.NO_DATA);
            }
            List<MediaModuleDetailEntity> entityList =  mediaModuleDetailMapper.searchListOfMediaModule(bankCode, branchCode, mediaModuleID);

            ObjectConvertUtils.toBean(entity, model);
            for (MediaModuleDetailEntity detailEntity : entityList) {
                MediaModuleDetailVO detailVO = new MediaModuleDetailVO();
                ObjectConvertUtils.toBean(detailEntity, detailVO);
                moduleDetailList.add(detailVO);
            }
            model.setMediaModuleDetailList(moduleDetailList);

            return UnifiedResponseManager.buildSearchSuccessResponse(ResponseDataConstant.NO_SEARCH_COUNT, model);
        } catch (Exception ex) {
            logger.error(ex.toString());
            return UnifiedResponseManager.buildExceptionResponse();
        }
    }

    @Override
    public UnifiedResponse changeMediaModuleOrder(MediaModuleDTO dto) {
        try {
            MediaModuleEntity currentEntity = mediaModuleMapper.searchByModuleID(dto.getBankCode(), dto.getBranchCode(), dto.getMediaModuleID());
            MediaModuleEntity swapEntity = mediaModuleMapper.searchByModuleID(dto.getBankCode(), dto.getBranchCode(), dto.getMediaModuleSwapID());
            if(currentEntity == null || swapEntity == null){
                return UnifiedResponseManager.buildSubmitSuccessResponse(ResponseDataConstant.NO_SEARCH_COUNT);
            }
            int affectRow = 0;
            int currentOrder = currentEntity.getMediaModuleOrder();
            int swapOrder = swapEntity.getMediaModuleOrder();

            currentEntity.setMediaModuleOrder(swapOrder);
            currentEntity.setUpdateUser(dto.getLoginUser());
            swapEntity.setMediaModuleOrder(currentOrder);
            swapEntity.setUpdateUser(dto.getLoginUser());

            affectRow += mediaModuleMapper.updateOrder(currentEntity);
            affectRow += mediaModuleMapper.updateOrder(swapEntity);
            return UnifiedResponseManager.buildSubmitSuccessResponse(affectRow);
        } catch (Exception ex) {
            logger.error(ex.toString());
            return UnifiedResponseManager.buildExceptionResponse();
        }
    }

    @Override
    public UnifiedResponse changeMediaModuleDetailOrder(MediaModuleDetailDTO dto) {
        try {
            MediaModuleDetailEntity currentEntity = mediaModuleDetailMapper.searchByMediaModuleDetailID(dto.getBankCode(), dto.getBranchCode(), dto.getMediaModuleID(), dto.getMediaDetailID());
            MediaModuleDetailEntity swapEntity = mediaModuleDetailMapper.searchByMediaModuleDetailID(dto.getBankCode(), dto.getBranchCode(), dto.getMediaModuleID(), dto.getMediaDetailSwapID());
            if(currentEntity == null || swapEntity == null){
                return UnifiedResponseManager.buildSubmitSuccessResponse(ResponseDataConstant.NO_SEARCH_COUNT);
            }
            int affectRow = 0;
            int currentOrder = currentEntity.getMediaDetailOrder();
            int swapOrder = swapEntity.getMediaDetailOrder();

            currentEntity.setMediaDetailOrder(swapOrder);
            currentEntity.setUpdateUser(dto.getLoginUser());
            swapEntity.setMediaDetailOrder(currentOrder);
            swapEntity.setUpdateUser(dto.getLoginUser());

            affectRow += mediaModuleDetailMapper.updateOrder(currentEntity);
            affectRow += mediaModuleDetailMapper.updateOrder(swapEntity);
            return UnifiedResponseManager.buildSubmitSuccessResponse(affectRow);
        } catch (Exception ex) {
            logger.error(ex.toString());
            return UnifiedResponseManager.buildExceptionResponse();
        }
    }

    @Override
    public UnifiedResponse add(MediaModuleDTO dto) {
        try{
            int affectRow = 0;
            MediaModuleEntity mediaModuleEntity = new MediaModuleEntity();

            //1、保存信息到基础数据表
            int currentMediaModuleMaxOrder = mediaModuleMapper.searchMaxOrder(dto.getBankCode(), dto.getBranchCode());

            ObjectConvertUtils.toBean(dto, mediaModuleEntity);
            mediaModuleEntity.setMediaModuleOrder(currentMediaModuleMaxOrder + 1);
            mediaModuleEntity.setCreateUser(dto.getLoginUser());
            mediaModuleEntity.setUpdateUser(dto.getLoginUser());
            affectRow = mediaModuleMapper.insert(mediaModuleEntity);

            //2、解析JSON
            List<MediaModuleDetailEntity> mediaModuleDetailEntityList = JsonUtils.deserializationToObject(dto.getDetailJson(), MediaModuleDetailEntity.class);

            //3、循环详细信息，逐条保存到详细信息表
            affectRow += addMediaModuleDetail(mediaModuleEntity, mediaModuleDetailEntityList);
            return UnifiedResponseManager.buildSubmitSuccessResponse(affectRow);

        }catch (Exception ex){
            logger.error(ex.toString());
            return UnifiedResponseManager.buildExceptionResponse();
        }
    }

    @Override
    public UnifiedResponse change(MediaModuleDTO dto) {
        try {
            int affectRow = 0;
            MediaModuleEntity mediaModuleEntity = new MediaModuleEntity();

            //1、更新基础信息表
            ObjectConvertUtils.toBean(dto, mediaModuleEntity);
            mediaModuleEntity.setUpdateUser(dto.getLoginUser());
            affectRow += mediaModuleMapper.update(mediaModuleEntity);

            //2、从JSON字符串解析详细信息
            List<MediaModuleDetailEntity> mediaModuleDetailEntityList = JsonUtils.deserializationToObject(dto.getDetailJson(), MediaModuleDetailEntity.class);

            //3、删除原有的详细信息
            affectRow += mediaModuleDetailMapper.deleteAll(dto.getBankCode(), dto.getBranchCode(), dto.getMediaModuleID());

            //4、循环详细信息，逐条保存到详细信息表
            affectRow += addMediaModuleDetail(mediaModuleEntity, mediaModuleDetailEntityList);
            return UnifiedResponseManager.buildSubmitSuccessResponse(affectRow);
        } catch (Exception ex) {
            logger.error(ex.toString());
            return UnifiedResponseManager.buildExceptionResponse();
        }
    }

    private int addMediaModuleDetail(MediaModuleEntity mediaModuleEntity, List<MediaModuleDetailEntity> mediaModuleDetailEntityList){
        int affectRow = 0;
        for (MediaModuleDetailEntity mediaModuleDetailEntity: mediaModuleDetailEntityList) {
            int currentMediaModuleDetailMaxOrder = mediaModuleDetailMapper.searchMaxOrder(mediaModuleDetailEntity.getBankCode(), mediaModuleDetailEntity.getBranchCode(), mediaModuleEntity.getMediaModuleID(), mediaModuleDetailEntity.getMediaDetailType());
            mediaModuleDetailEntity.setMediaModuleID(mediaModuleEntity.getMediaModuleID());
            mediaModuleDetailEntity.setMediaDetailOrder(currentMediaModuleDetailMaxOrder + 1);
            mediaModuleDetailEntity.setCreateUser(mediaModuleEntity.getUpdateUser());
            mediaModuleDetailEntity.setUpdateUser(mediaModuleEntity.getUpdateUser());
            affectRow += mediaModuleDetailMapper.insert(mediaModuleDetailEntity);
        }
        return affectRow;
    }

    @Override
    public UnifiedResponse changeDataStatus(MediaModuleDTO dto) {
        try {
            MediaModuleEntity entity = new MediaModuleEntity();
            ObjectConvertUtils.toBean(dto, entity);
            entity.setUpdateUser(dto.getLoginUser());
            int affectRow = mediaModuleMapper.updateDataStatus(entity);
            return UnifiedResponseManager.buildSubmitSuccessResponse(affectRow);
        } catch (Exception ex) {
            logger.error(ex.toString());
            return UnifiedResponseManager.buildExceptionResponse();
        }
    }

    @Override
    public UnifiedResponse deleteMediaModule(String bankCode, String branchCode, int mediaModuleID) {
        try {
            int affectRow = 0;
            affectRow += mediaModuleMapper.deleteMediaModule(bankCode, branchCode, mediaModuleID);
            affectRow += mediaModuleDetailMapper.deleteAll(bankCode, branchCode, mediaModuleID);
            return UnifiedResponseManager.buildSubmitSuccessResponse(affectRow);
        } catch (Exception ex) {
            logger.error(ex.toString());
            return UnifiedResponseManager.buildExceptionResponse();
        }
    }

    @Override
    public UnifiedResponse deleteMediaModuleDetail(String bankCode, String branchCode, int mediaModuleID, int mediaDetailID) {
        try {
            int affectRow = mediaModuleDetailMapper.deleteDetail(bankCode, branchCode, mediaModuleID, mediaDetailID);
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
    public UnifiedResponse delete(int id) {
        return null;
    }
}

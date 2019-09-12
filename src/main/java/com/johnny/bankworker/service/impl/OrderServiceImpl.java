package com.johnny.bankworker.service.impl;

import com.johnny.bankworker.common.ObjectConvertUtils;
import com.johnny.bankworker.constant.DataStatusConstant;
import com.johnny.bankworker.constant.ResponseDataConstant;
import com.johnny.bankworker.dto.OrderDTO;
import com.johnny.bankworker.entity.OrderDetailEntity;
import com.johnny.bankworker.entity.OrderEntity;
import com.johnny.bankworker.manager.UnifiedResponseManager;
import com.johnny.bankworker.mapper.OrderDetailMapper;
import com.johnny.bankworker.mapper.OrderMapper;
import com.johnny.bankworker.service.BaseService;
import com.johnny.bankworker.vo.OrderDetailVO;
import com.johnny.bankworker.vo.OrderVO;
import com.johnny.bankworker.vo.UnifiedResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderServiceImpl implements BaseService<OrderDTO> {
    @Autowired
    private OrderMapper myMapper;

    @Autowired
    private OrderDetailMapper detailMapper;

    private Logger logger = LogManager.getLogger(OrderServiceImpl.class);

    @Override
    public UnifiedResponse findList(int pageNumber, int pageSize, String dataStatus) {
        try {
            int startIndex = (pageNumber - 1) * pageSize;
            List<OrderVO> modelList = new ArrayList<>();

            int totalCount = myMapper.searchTotalCount(dataStatus.equals(DataStatusConstant.All) ? null : dataStatus);
            if(totalCount == 0){
                return UnifiedResponseManager.buildSearchSuccessResponse(ResponseDataConstant.NO_SEARCH_COUNT, ResponseDataConstant.NO_DATA);
            }

            List<OrderEntity> entityList =  myMapper.searchList(startIndex, pageSize, dataStatus.equals(DataStatusConstant.All) ? null : dataStatus);
            for (OrderEntity entity : entityList) {
                List<OrderDetailVO> orderDetailModelList = new ArrayList<>();

                List<OrderDetailEntity> orderDetailEntityList = detailMapper.searchListByOrderID(entity.getOrderID());
                for (OrderDetailEntity detailEntity : orderDetailEntityList){
                    OrderDetailVO detailModel = new OrderDetailVO();
                    ObjectConvertUtils.toBean(detailEntity, detailModel);
                    orderDetailModelList.add(detailModel);
                }
                OrderVO model = new OrderVO();
                ObjectConvertUtils.toBean(entity, model);
                model.setOrderDetailList(orderDetailModelList);
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
        return null;
    }

    @Override
    public UnifiedResponse existCheck(String value) {
        return null;
    }

    @Override
    public UnifiedResponse add(OrderDTO dto) {
        try {
            OrderEntity entity = new OrderEntity();
            String[] orderItems = dto.getOrderItemList().split(",");

            ObjectConvertUtils.toBean(dto, entity);
            entity.setCreateUser(dto.getLoginUser());
            entity.setUpdateUser(dto.getLoginUser());
            int affectRow4Order = myMapper.insert(entity);

            int affectRow4OrderDetail = 0;
            for(String item : orderItems){
                OrderDetailEntity detailEntity = new OrderDetailEntity();
                detailEntity.setOrderID(entity.getOrderID());
                detailEntity.setSystemID(Integer.parseInt(item));
                detailEntity.setCreateUser(dto.getLoginUser());
                detailEntity.setUpdateUser(dto.getLoginUser());
                affectRow4OrderDetail += detailMapper.insert(detailEntity);
            }

            return UnifiedResponseManager.buildSubmitSuccessResponse(affectRow4Order + affectRow4OrderDetail);
        } catch (Exception ex) {
            logger.error(ex.toString());
            return UnifiedResponseManager.buildExceptionResponse();
        }
    }

    @Override
    public UnifiedResponse change(OrderDTO dto) {
        return null;
    }

    @Override
    public UnifiedResponse changeDataStatus(OrderDTO dto) {
        try {
            OrderEntity entity = new OrderEntity();
            ObjectConvertUtils.toBean(dto, entity);
            entity.setUpdateUser(dto.getLoginUser());
            int affectRow = myMapper.updateDataStatus(entity);
            return UnifiedResponseManager.buildSubmitSuccessResponse(affectRow);
        } catch (Exception ex) {
            logger.error(ex.toString());
            return UnifiedResponseManager.buildExceptionResponse();
        }
    }

    @Override
    public UnifiedResponse delete(int id) {
        return null;
    }
}

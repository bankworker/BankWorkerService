package com.johnny.bankworker.mapper;

import com.johnny.bankworker.entity.OrderDetailEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface OrderDetailMapper extends BaseMapper<OrderDetailEntity> {
    List<OrderDetailEntity> searchListByOrderID(int orderID);

}

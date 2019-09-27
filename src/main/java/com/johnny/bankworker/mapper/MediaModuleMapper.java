package com.johnny.bankworker.mapper;

import com.johnny.bankworker.entity.MediaModuleEntity;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MediaModuleMapper extends BaseMapper<MediaModuleEntity> {
    MediaModuleEntity searchByModuleID(String bankCode, String branchCode, int mediaModuleID);

    int searchMaxOrder(String bankCode, String branchCode);

    int updateOrder(MediaModuleEntity entity);

    int deleteMediaModule(String bankCode, String branchCode, int mediaModuleID);
}

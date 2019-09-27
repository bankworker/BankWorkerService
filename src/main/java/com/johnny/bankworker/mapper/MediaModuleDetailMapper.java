package com.johnny.bankworker.mapper;

import com.johnny.bankworker.entity.MediaModuleDetailEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MediaModuleDetailMapper extends BaseMapper<MediaModuleDetailEntity> {
    List<MediaModuleDetailEntity> searchListOfMediaModule(String bankCode, String branchCode, int mediaModuleID);

    MediaModuleDetailEntity searchByMediaModuleDetailID(String bankCode, String branchCode, int mediaModuleID, int mediaDetailID);

    int searchMaxOrder(String bankCode, String branchCode, int mediaModuleID, String mediaDetailType);

    int updateOrder(MediaModuleDetailEntity entity);

    int deleteAll(String bankCode, String branchCode, int mediaModuleID);

    int deleteDetail(String bankCode, String branchCode, int mediaModuleID, int mediaDetailID);
}

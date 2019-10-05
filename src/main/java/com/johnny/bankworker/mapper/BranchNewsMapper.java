package com.johnny.bankworker.mapper;

import com.johnny.bankworker.entity.BranchNewsEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BranchNewsMapper extends BaseMapper<BranchNewsEntity> {
    int searchTotalCount4Branch(String bankCode, String branchCode);

    BranchNewsEntity searchByNewsID(String bankCode, String branchCode, int newsID);

    List<BranchNewsEntity> searchList4Branch(int startIndex, int pageSize, String bankCode, String branchCode);
}

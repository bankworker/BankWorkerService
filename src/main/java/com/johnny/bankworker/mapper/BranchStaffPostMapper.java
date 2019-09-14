package com.johnny.bankworker.mapper;

import com.johnny.bankworker.entity.BranchStaffPostEntity;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface BranchStaffPostMapper extends BaseMapper<BranchStaffPostEntity> {
    int existCheck4Branch(String bankCode, String branchCode, String staffPostName);

    int searchStaffPostIsUsingCount(String bankCode, String branchCode, int staffPostID);
}

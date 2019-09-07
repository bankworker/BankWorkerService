package com.johnny.bankworker.mapper;

import com.johnny.bankworker.entity.BankBranchEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BankBranchMapper extends BaseMapper<BankBranchEntity> {
    List<BankBranchEntity> searchListByBankCode(String bankCode);

    BankBranchEntity searchListByCode(String bankCode, String branchCode);

    int updateLogo(BankBranchEntity entity);
}

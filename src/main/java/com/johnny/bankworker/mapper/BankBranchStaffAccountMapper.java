package com.johnny.bankworker.mapper;

import com.johnny.bankworker.entity.BankBranchStaffAccountEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BankBranchStaffAccountMapper extends BaseMapper<BankBranchStaffAccountEntity> {
    BankBranchStaffAccountEntity login(String account, String password, int systemID);

    List<BankBranchStaffAccountEntity> searchAuthorizedSystem(String account, String password);
}

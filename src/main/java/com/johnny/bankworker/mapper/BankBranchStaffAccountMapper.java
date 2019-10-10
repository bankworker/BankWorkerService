package com.johnny.bankworker.mapper;

import com.johnny.bankworker.entity.BankBranchStaffAccountEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BankBranchStaffAccountMapper extends BaseMapper<BankBranchStaffAccountEntity> {
    BankBranchStaffAccountEntity login(String account, String password, int systemID);

    BankBranchStaffAccountEntity loginBalance(String bankCode, String branchCode, int postID, String cellphone, String password);

    List<BankBranchStaffAccountEntity> searchAuthorizedSystem(String bankCode, String branchCode);

    int updateByAccount(BankBranchStaffAccountEntity entity);

    int updatePassword(BankBranchStaffAccountEntity entity);

    int deleteByAccount(BankBranchStaffAccountEntity entity);
}

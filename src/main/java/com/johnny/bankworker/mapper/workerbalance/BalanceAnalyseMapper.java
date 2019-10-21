package com.johnny.bankworker.mapper.workerbalance;

import com.johnny.bankworker.entity.BalanceAnalyse4FinancialClockEntity;
import com.johnny.bankworker.entity.BalanceAnalyse4FinancialEntity;
import com.johnny.bankworker.entity.BalanceAnalyse4LobbyEntity;
import com.johnny.bankworker.entity.BalanceCallbackAnalyseEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BalanceAnalyseMapper {
    List<BalanceAnalyse4FinancialEntity> searchFinancialBusinessAnalyse(String bankCode, String branchCode, String fromDate, String toDate);

    List<BalanceAnalyse4FinancialClockEntity> searchFinancialClockList(String bankCode, String branchCode, int receiverID, String fromDate, String toDate);

    List<BalanceCallbackAnalyseEntity> searchCallbackBusinessAnalyse4Financial(String bankCode, String branchCode, int receiverID, String fromDate, String toDate);

    List<BalanceAnalyse4LobbyEntity> searchLobbyBusinessAnalyse(String bankCode, String branchCode, String fromDate, String toDate);

    List<BalanceCallbackAnalyseEntity> searchCallbackBusinessAnalyse4Lobby(String bankCode, String branchCode, int senderID, String fromDate, String toDate);
}

package com.johnny.bankworker.vo;

import lombok.Data;

@Data
public class BalanceAnalyse4LobbyVO {
    private int lobbyID;
    private String lobbyName;
    private int processedBusinessCount;
    private int processedCallbackCount;
}

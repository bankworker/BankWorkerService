package com.johnny.bankworker.entity;

import lombok.Data;

@Data
public class BalanceAnalyse4LobbyEntity {
    private int lobbyID;
    private String lobbyName;
    private int processedBusinessCount;
    private int processedCallbackCount;
}

package com.johnny.bankworker.entity;

import lombok.Data;

@Data
public class BaseEntity {
    private String dataStatus;
    private String dataStatusText;
    private String createUser;
    private String createTime;
    private String updateUser;
    private String updateTime;
}

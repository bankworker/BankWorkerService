package com.johnny.bankworker.vo;

import lombok.Data;

@Data
class BaseVO {
    private String dataStatus;
    private String dataStatusText;
    private String createUser;
    private String createTime;
    private String updateUser;
    private String updateTime;
}

package com.johnny.bankworker.vo;

import lombok.Data;

@Data
public class ChinaMappingVO extends BaseVO {
    private int regionID;
    private int regionCode;
    private String regionName;
    private int regionParent;
}

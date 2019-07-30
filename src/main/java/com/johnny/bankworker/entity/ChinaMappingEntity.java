package com.johnny.bankworker.entity;

import lombok.Data;

@Data
public class ChinaMappingEntity extends BaseEntity {
    private int regionID;
    private int regionCode;
    private String regionName;
    private int regionParent;
}

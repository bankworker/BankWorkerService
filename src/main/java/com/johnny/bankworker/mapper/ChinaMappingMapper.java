package com.johnny.bankworker.mapper;

import com.johnny.bankworker.entity.ChinaMappingEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ChinaMappingMapper extends BaseMapper<ChinaMappingEntity> {
    List<ChinaMappingEntity> searchByParentCode(int parentCode);
}

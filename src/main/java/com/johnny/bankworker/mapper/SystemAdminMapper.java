package com.johnny.bankworker.mapper;

import com.johnny.bankworker.entity.SystemAdminEntity;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface SystemAdminMapper extends BaseMapper<SystemAdminEntity> {
    SystemAdminEntity login(String cellphone, String password);

    int updatePassword(SystemAdminEntity entity);
}

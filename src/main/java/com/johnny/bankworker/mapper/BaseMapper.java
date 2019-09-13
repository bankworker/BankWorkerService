package com.johnny.bankworker.mapper;

import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BaseMapper<T> {
    int searchTotalCount(@Param("dataStatus")  String dataStatus);

    int searchTotalCount4Branch(String bankCode, String branchCode);

    List<T> searchList(int startIndex, int pageSize, String dataStatus);

    List<T> searchList4Branch(int startIndex, int pageSize, String bankCode, String branchCode);

    T searchByID(int id, String dataStatus);

    int existCheck(String value);

    int insert(T entity);

    int update(T entity);

    int updateDataStatus(T entity);

    int delete(int id);
}

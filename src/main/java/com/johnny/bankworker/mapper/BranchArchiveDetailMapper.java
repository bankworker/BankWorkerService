package com.johnny.bankworker.mapper;

import com.johnny.bankworker.entity.BranchArchiveDetailEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BranchArchiveDetailMapper extends BaseMapper<BranchArchiveDetailEntity> {
    /**
     * 查询指定支行，某个档案的详细信息
     * @param bankCode 银行编码
     * @param branchCode 支行编码
     * @param archiveID 档案编号
     * @return 返回档案详细信息
     */
    List<BranchArchiveDetailEntity> searchListOfArchive(String bankCode, String branchCode, int archiveID);

    /**
     * 通过总行编码、支行编码、档案编号查询对应的一条档案详细信息
     * @param bankCode 总行编码
     * @param branchCode 支行编码
     * @param archiveID 档案编号
     * @param archiveDetailID 档案明细编号
     * @return 返回对应的一条档案明细信息
     */
    BranchArchiveDetailEntity searchByArchiveDetailID(String bankCode, String branchCode, int archiveID, int archiveDetailID);

    /**
     * 查询指定支行，某个档案下的子档案，当前最大的顺序值
     * @param bankCode 总行编码
     * @param branchCode 支行编码
     * @param archiveID 所属指标编号
     * @param archiveDetailType 指标明细类型
     * @return 返回当前档案下的子档案最大的顺序值
     */
    int searchMaxOrder(String bankCode, String branchCode, int archiveID, String archiveDetailType);


    /**
     * 删除指定支行的某个档案的详细信息
     * @param bankCode 银行编码
     * @param branchCode 支行编码
     * @param archiveID 档案编号
     * @return 返回删除后的响应行数
     */
    int deleteOfArchive(String bankCode, String branchCode, int archiveID);

    /**
     * 删除指定支行，某个档案中的指定信息
     * @param bankCode 银行编码
     * @param branchCode 支行编码
     * @param archiveDetailID 档案详细编号
     * @return 返回删除后的响应行数
     */
    int deleteOfArchiveDetail(String bankCode, String branchCode, int archiveID, int archiveDetailID);

    /**
     * 移动档案详细内容的顺序
     * @param entity 档案详细信息
     * @return 返回更新后的影响行数
     */
    int updateOrder(BranchArchiveDetailEntity entity);
}

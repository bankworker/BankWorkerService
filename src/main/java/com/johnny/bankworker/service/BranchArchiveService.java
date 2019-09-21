package com.johnny.bankworker.service;

import com.johnny.bankworker.dto.BranchArchiveDTO;
import com.johnny.bankworker.vo.UnifiedResponse;

public interface BranchArchiveService extends BaseService<BranchArchiveDTO> {
    /**
     * 查询指定支行的档案内容
     * @param pageNumber 页码
     * @param pageSize 每页数量
     * @param bankCode 银行编码
     * @param branchCode 支行编码
     * @return 返回支行档案内容
     */
    UnifiedResponse findList(int pageNumber, int pageSize, String bankCode, String branchCode);

    /**
     * 校验指定支行中，某个档案是否已经存在
     * @param bankCode 银行编码
     * @param branchCode 支行编码
     * @param archiveName 档案名称
     * @return 返回是否存在的结果
     */
    UnifiedResponse existCheck(String bankCode, String branchCode, String archiveName);

    /**
     * 校验指定支行中，某个档案下是否存在子档案
     * @param bankCode 银行编码
     * @param branchCode 支行编码
     * @param archiveParentID 父层级档案编号
     * @return 返回是否存在的结果
     */
    UnifiedResponse existCheck(String bankCode, String branchCode, int archiveParentID);

    /**
     * 修改指定支行档案内容的所在位置
     * @param dto 档案内容
     * @return 返回更新后的结果
     */
    UnifiedResponse changePosition(BranchArchiveDTO dto);

    /**
     * 修改指定支行档案内容的显示顺序
     * @param dto 档案内容
     * @return 返回更新后的结果
     */
    UnifiedResponse changeOrder(BranchArchiveDTO dto);

    /**
     * 删除指定支行的指标
     * @param bankCode 银行编码
     * @param branchCode 支行编码
     * @param archiveID 指标编号
     * @return 返回删除后的影响行数
     */
    UnifiedResponse delete(String bankCode, String branchCode, int archiveID);
}

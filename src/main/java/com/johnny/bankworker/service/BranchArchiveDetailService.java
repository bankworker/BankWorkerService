package com.johnny.bankworker.service;

import com.johnny.bankworker.dto.BranchArchiveDetailDTO;
import com.johnny.bankworker.vo.UnifiedResponse;

public interface BranchArchiveDetailService extends BaseService<BranchArchiveDetailDTO> {
    /**
     * 查询指定支行某个档案的详细内容
     * @param bankCode 银行编码
     * @param branchCode 支行编码
     * @param archiveID 档案编号
     * @return 返回档案的详细内容
     */
    UnifiedResponse findList(String bankCode, String branchCode, int archiveID);

    /**
     * 删除指定支行的某个档案的详细信息
     * @param bankCode 银行编码
     * @param branchCode 支行编码
     * @param archiveID 档案编号
     * @return 返回删除后的结果
     */
    UnifiedResponse deleteOfArchive(String bankCode, String branchCode, int archiveID);

    /**
     * 删除指定支行，某个档案中的指定信息
     * @param bankCode 银行编码
     * @param branchCode 支行编码
     * @param archiveDetailID 档案详细编号
     * @return 返回删除后的结果
     */
    UnifiedResponse deleteOfArchiveDetail(String bankCode, String branchCode, int archiveID, int archiveDetailID);

    /**
     * 移动档案详细内容的顺序
     * @param dto 档案详细信息
     * @return 返回更新后的结果
     */
    UnifiedResponse changeOrder(BranchArchiveDetailDTO dto);
}

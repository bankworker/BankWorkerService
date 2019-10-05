package com.johnny.bankworker.mapper;

import com.johnny.bankworker.entity.BranchArchiveEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BranchArchiveMapper extends BaseMapper<BranchArchiveEntity> {

    /**
     * 通过总行编码、支行编码、档案编号查询对应的一条档案信息
     * @param bankCode 总行编码
     * @param branchCode 支行编码
     * @param archiveID 档案编号
     * @return 返回对应的一条档案信息
     */
    BranchArchiveEntity searchByArchiveID(String bankCode, String branchCode, int archiveID);

    /**
     * 通过总行编码、支行编码、档案名称查询对应的一条档案信息，可用于校验当前档案名称是否存在
     * @param bankCode 总行编码
     * @param branchCode 支行编码
     * @param archiveName 档案名称
     * @return 返回对应的一条档案信息
     */
    int existCheck(String bankCode, String branchCode, String archiveName);

    /**
     * 通过总行编码、支行编码、父层级的档案编号查询其下的档案信息
     * @param bankCode 总行编码
     * @param branchCode 支行编码
     * @param archiveParentID 父层级的档案编号
     * @param dataStatus 数据状态
     * @return 反馈父层级档案下的所有档案信息
     */
    List<BranchArchiveEntity> searchByParentArchive(String bankCode, String branchCode, int archiveParentID, String dataStatus);

    /**
     * 查询指定支行，某个档案下的子档案，当前最大的顺序值
     * @param bankCode 总行编码
     * @param branchCode 支行编码
     * @param archiveParentID 父层级的档案编号
     * @return 返回当前档案下的子档案最大的顺序值
     */
    int searchMaxOrder(String bankCode, String branchCode, int archiveParentID);

    /**
     * 调整指定档案的位置
     * @param entity 档案信息
     * @return 调整后的影响行数
     */
    int updatePosition(BranchArchiveEntity entity);

    /**
     * 修改指定档案的顺序
     * @param entity 档案信息
     * @return 调整后的影响行数
     */
    int updateOrder(BranchArchiveEntity entity);

    /**
     * 删除指定指标
     * @param bankCode 总行编码
     * @param branchCode 支行编码
     * @param archiveID 指标编号
     * @return 返回删除后的影响行数
     */
    int deleteArchive(String bankCode, String branchCode, int archiveID);
}

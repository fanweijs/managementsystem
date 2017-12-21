package com.company.dao;

import com.company.model.RewardPublish;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by 柏欢欢 on 2017/12/20.
 */
public interface RewardPublishDAO {
    /**
     * 增加一条奖惩信息
     * @param rewardPublish
     * @return int
     * @throws SQLException
     */
    int insertRP(RewardPublish rewardPublish) throws SQLException;

    /**
     * 批量删除奖惩信息
     * @param ids
     * @return int[]
     * @throws SQLException
     */
    int[] batchDelteRP(List<Integer> ids) throws SQLException;

    /**
     * 获得所有奖惩信息
     * @return list
     * @throws SQLException
     */
    List<RewardPublish> getAll() throws SQLException;

    /**
     * 根据账号获得其奖惩信息
     * @param account
     * @return list
     * @throws SQLException
     */
    List<RewardPublish> getRP(String account) throws SQLException;

    /**
     * 根据关键字查找奖惩信息
     * @param keywords
     * @return list
     * @throws SQLException
     */
    List<RewardPublish> queryLike(String keywords) throws SQLException;

    /**
     * 按条件筛选奖惩信息
     * @param condition
     * @return list
     * @throws SQLException
     */
    List<RewardPublish> queryFilter(String condition) throws SQLException;
}

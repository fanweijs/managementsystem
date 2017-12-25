package com.company.service;

import com.company.model.RewardPublish;

import java.util.List;

/**
 * Created by 柏欢欢 on 2017/12/21.
 */
public interface RpService {
    /**
     * 获取某个员工的奖惩列表
     * @param account
     * @return list
     */
    List<RewardPublish> getRP(String account);

    /**
     * 插入一行奖惩信息
     * @param rp
     * @return int
     */
    int insertRP(RewardPublish rp);

    /**
     * 批量删除奖惩信息
     * @param ids
     * @return int
     */
    int[] bathchDeleteRP(List<Integer> ids);

    /**
     * 获得所有奖惩信息
     * @return list
     */
    List<RewardPublish> getAllRP();

    /**
     * 筛选奖惩信息
     * @param condition
     * @return list
     */
    List<RewardPublish> queryFilter(String condition);

    /**
     * 关键字查找奖惩信息
     * @param keywords
     * @return list
     */
    List<RewardPublish> queryLike(String keywords);
}

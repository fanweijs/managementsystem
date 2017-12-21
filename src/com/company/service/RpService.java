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
     * @return
     */
    List<RewardPublish> getRP(String account);
}

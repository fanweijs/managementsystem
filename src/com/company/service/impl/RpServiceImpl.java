package com.company.service.impl;


import com.company.dao.RewardPublishDAO;
import com.company.factory.DAOFactory;
import com.company.model.RewardPublish;
import com.company.service.RpService;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by 柏欢欢 on 2017/12/21.
 */
public class RpServiceImpl implements RpService{
    private RewardPublishDAO rewardPublishDAO = DAOFactory.getRewardPublishInstance();
    @Override
    public List<RewardPublish> getRP(String account) {
        List<RewardPublish> list = null;
        try {
            list = rewardPublishDAO.getRP(account);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public int insertRP(RewardPublish rp) {
        int n = 0;
        try {
            n = rewardPublishDAO.insertRP(rp);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return  n;
    }

    @Override
    public int[] bathchDeleteRP(List<Integer> ids) {
        int[] result = new int[ids.size()];
        try {
            result = rewardPublishDAO.batchDelteRP(ids);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public List<RewardPublish> getAllRP() {
        List<RewardPublish> rewardPublishList = null;
        try {
            rewardPublishList = rewardPublishDAO.getAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rewardPublishList;
    }

    @Override
    public List<RewardPublish> queryFilter(String condition) {
        List<RewardPublish> rewardPublishList = new ArrayList<>();
        try {
            rewardPublishList = rewardPublishDAO.queryFilter(condition);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rewardPublishList;
    }

    @Override
    public List<RewardPublish> queryLike(String keywords) {
        List<RewardPublish> rewardPublishList = new ArrayList<>();
        try {
            rewardPublishList = rewardPublishDAO.queryLike(keywords);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rewardPublishList;
    }
}

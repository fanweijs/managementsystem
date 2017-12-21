package com.company.service.impl;


import com.company.dao.RewardPublishDAO;
import com.company.factory.DAOFactory;
import com.company.model.RewardPublish;
import com.company.service.RpService;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by 柏欢欢 on 2017/12/21.
 */
public class RpServiceImpl implements RpService {
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
}

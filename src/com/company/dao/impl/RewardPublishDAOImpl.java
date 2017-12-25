package com.company.dao.impl;

import com.company.dao.RewardPublishDAO;
import com.company.model.RewardPublish;
import com.company.utils.JDBCUtil;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by 柏欢欢 on 2017/12/20.
 */
public class RewardPublishDAOImpl implements RewardPublishDAO {
    private JDBCUtil jdbcUtil = JDBCUtil.getInitJDBCUtil();
    @Override
    public int insertRP(RewardPublish rewardPublish) throws SQLException {
        String sql = "INSERT INTO t_rp VALUES (?,?,?,?,?) ";
        Object[] params = {rewardPublish.getId(),rewardPublish.getAccount(), rewardPublish.getFlag(), rewardPublish.getRp_name(), rewardPublish.getRp_time()};
        int n = jdbcUtil.executeUpdate(sql, params);
        return n;
    }

    @Override
    public int[] batchDelteRP(List<Integer> ids) throws SQLException {
        int[] result;
        Connection connection = jdbcUtil.getConnection();
        String sql = "DELETE FROM t_rp WHERE id = ? ";
        PreparedStatement ps = connection.prepareStatement(sql);
        for (Integer id : ids) {
            ps.setInt(1, id);
            ps.addBatch();
        }
        result = ps.executeBatch();
        jdbcUtil.closeAll();
        return result;
    }


    @Override
    public List<RewardPublish> getRP(String account) throws SQLException {
        String sql = "SELECT * FROM t_rp WHERE account = ? ";
        List<Object> list = jdbcUtil.excuteQuery(sql, new Object[]{account});
        List<RewardPublish> rpList = new ArrayList<>();
        for (Object object : list) {
            Map<String, Object> map = (Map<String, Object>) object;
            RewardPublish rp = new RewardPublish((Integer) map.get("id"),map.get("account").toString(), map.get("flag").toString(),
                    map.get("rp_name").toString(),(Date) map.get("rp_time"));
            //给id设置值
            rp.setId((Integer) map.get("id"));
            rpList.add(rp);
        }
        return rpList;
    }


    @Override
    public List<RewardPublish> getAll() throws SQLException {
        String sql = "SELECT * FROM t_rp ";
        List<Object> list = jdbcUtil.excuteQuery(sql, null);
        return getRPList(list);
    }

    @Override
    public List<RewardPublish> queryLike(String keywords) throws SQLException {
        String sql = "SELECT * FROM t_rp WHERE CONCAT(account,flag,rp_name,rp_time) LIKE ? ";
        List<Object> list = jdbcUtil.excuteQuery(sql, new Object[]{"%" + keywords + "%"});
        return getRPList(list);
    }

    @Override
    public List<RewardPublish> queryFilter(String condition) throws SQLException {
        String sql = "SELECT * FROM t_rp " + condition;
        List<Object> list = jdbcUtil.excuteQuery(sql, null);
        return getRPList(list);
    }
    /**
     * 封装一个将List<Object>集合转换成奖惩集合的方法
     *
     * @param list
     * @return
     */
    private List<RewardPublish> getRPList(List<Object> list) {
        List<RewardPublish> rpList = new ArrayList<>();
        for (Object object : list) {
            Map<String, Object> map = (Map<String, Object>) object;
            RewardPublish rp = new RewardPublish((Integer)map.get("id"),map.get("account").toString(), map.get("flag").toString(),
                    map.get("rp_name").toString(), (Date) map.get("rp_time"));
            //给id设置值
            rp.setId((Integer) map.get("id"));
            rpList.add(rp);
        }
        return rpList;
    }
}

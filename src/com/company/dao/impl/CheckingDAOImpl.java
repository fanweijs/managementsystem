package com.company.dao.impl;

import com.company.dao.CheckingDAO;
import com.company.model.Checking;
import com.company.model.Login;
import com.company.utils.JDBCUtil;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by 咸赛 on 2017/12/20.
 */
public class CheckingDAOImpl implements CheckingDAO {
    private JDBCUtil jdbcUtil = JDBCUtil.getInitJDBCUtil();

    @Override
    public List<Checking> getChecking(String check_id) throws SQLException {
        String sql = "SELECT * FROM t_checking WHERE check_id = ? ";
        List<Object> list = jdbcUtil.excuteQuery(sql, new Object[]{check_id});
        return getCheckinglist(list);
    }

    @Override
    public List<Checking> getAll() throws SQLException {
        String sql = "SELECT * FROM t_checking ";
        List<Object> list = jdbcUtil.excuteQuery(sql, null);
        return getCheckinglist(list);
    }

    @Override
    public List<String> getAllAccount() throws SQLException {
        String sql = "SELECT * FROM t_login";
        List<Object> list = jdbcUtil.excuteQuery(sql,null);
        return getLoginList(list);
    }

    @Override
    public int insert(Login logins) throws SQLException {
//        String sql = "INSERT INTO t_checking VALUES (null,?,?,?,?) ";
//        Object[] params = {checking.getCheck_id(),checking.getAccount(),checking.getDatetime(),checking.getCondition()};
//        int n = jdbcUtil.executeUpdate(sql, params);
//        return n;
        return 0;
    }

    @Override
    public int[] bachIntsert(List<String> list) throws SQLException {
        return new int[0];
    }


    @Override
    public int[] bachDelete(List<Integer> check_id) throws SQLException {
        int[] result;
        Connection connection = jdbcUtil.getConnection();
        String sql = "DELETE FROM t_reward_publish WHERE id = ? ";
        PreparedStatement ps = connection.prepareStatement(sql);
        for (Integer id : check_id) {
            ps.setInt(1, id);
            ps.addBatch();
        }
        result = ps.executeBatch();
        jdbcUtil.closeAll();
        return result;
    }


    private List<Checking>getCheckinglist(List<Object>list){
        List<Checking>checkingList = new ArrayList<>();
        for (Object object:list){
            Map<String,Object>map = (Map<String,Object>)object;
            Checking checking = new Checking((Integer)map.get("check_id"),map.get("account").toString(),
                    (Date)map.get("datetime"),map.get("condition").toString());
            checking.setCheck_id((Integer)map.get("id"));
            checkingList.add(checking);
        }
        return checkingList;
    }
    private List<String>getLoginList(List<Object>list){
        List<String>LoginList = new ArrayList<>();
        for (Object object:list){
            Map<String,Object>map = (Map<String,Object>)object;
            LoginList.add(map.get("login_account").toString());
        }
        return LoginList;
    }
}

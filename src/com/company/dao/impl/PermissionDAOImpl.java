package com.company.dao.impl;

import com.company.dao.PermissionDAO;
import com.company.utils.JDBCUtil;

import java.sql.SQLException;
import java.util.*;

/**
 * @author 樊委
 * 2017-12-18
 */
public class PermissionDAOImpl implements PermissionDAO {
    private JDBCUtil jdbcUtil = JDBCUtil.getInitJDBCUtil();
    @Override
    public List<Integer> getPermissionItem(String account) throws SQLException {
        String sql = "SELECT * FROM t_relation WHERE account =  "+account;
        List<Object> list = jdbcUtil.excuteQuery(sql, null);
        return getItem(list);
    }

    @Override
    public String getOnePermissionGroup(int a) throws SQLException {
        String sql = "SELECT * FROM t_permission_item WHERE id = ? ";
        Map<String, Object> map = jdbcUtil.executeQuerySingle(sql, new Object[]{a});
        return map.get("groupid").toString();
    }

    @Override
    public Set<Integer> getAllPermissionGroup(List<Integer> list) throws SQLException {
        Set<Integer>set = new HashSet<>();
        for (int a:list){
        set.add(Integer.valueOf(getOnePermissionGroup(a)));
       }
       return set;
    }

    @Override
    public String getNamePermissionGroup(int id) throws SQLException {
        String sql = "SELECT * FROM t_permission_group WHERE id = ? ";
        Map<String, Object> map = jdbcUtil.executeQuerySingle(sql, new Object[]{id});
        return map.get("name").toString();
    }

    @Override
    public String getNamePermissionItem(int id) throws SQLException {
        String sql = "SELECT * FROM t_permission_item WHERE id = ? ";
        Map<String, Object> map = jdbcUtil.executeQuerySingle(sql, new Object[]{id});
        return map.get("name").toString();
    }

    @Override
    public Map<String, List<String>> getGroupAndItem(String account) throws SQLException {
        return null;
    }


    private List<Integer> getItem(List<Object> list) {
        List<Integer> items = new ArrayList<>();
        for (Object object : list) {
            Map<String, Object> map = (Map<String, Object>) object;
            items.add((Integer)map.get("item"));
        }
        return items;
    }
}

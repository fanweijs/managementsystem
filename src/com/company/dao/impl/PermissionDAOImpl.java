package com.company.dao.impl;

import com.company.dao.InfoDAO;
import com.company.dao.PermissionDAO;
import com.company.utils.JDBCUtil;

import java.awt.*;
import java.sql.SQLException;
import java.util.*;
import java.util.List;

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
        Map<String,List<String>> map = new HashMap<>();
        List<String>infos=new ArrayList<>();//通知
        List<String>record=new ArrayList<>();//档案
        List<String>money=new ArrayList<>();//财务
        List<String>checkings=new ArrayList<>();//考勤
        List<String>rps=new ArrayList<>();//奖惩
        List<String>permission = new ArrayList<>();//权限
        List<Integer>itemList = new ArrayList<>();
        itemList = getPermissionItem(account);
//        Set<Integer>set = new HashSet<>();
//         set =getAllPermissionGroup(itemList);
        for(int a:itemList){
            String id = getOnePermissionGroup(a);
            String itemName = getNamePermissionItem(a);
            if(id.equals("1")){
                record.add(itemName);
            }
            if(id.equals("2")){
                checkings.add(itemName);
            }
            if(id.equals("3")){
                rps.add(itemName);
            }
            if(id.equals("4")){
                money.add(itemName);
            }
            if(id.equals("5")){
                infos.add(itemName);
            }
            if(id.equals("6")){
                permission.add(itemName);
            }
        }
        map.put("通知",infos);
        map.put("档案",record);
        map.put("财务",money);
        map.put("考勤",checkings);
        map.put("奖惩",rps);
        map.put("权限",permission);
       return map;
    }

    @Override
    public List<String> getAllGroupName(String account) throws SQLException {
       List<Integer>list = getPermissionItem(account);
       Set<Integer>set = getAllPermissionGroup(list);
       List<String>allGroupName = new ArrayList<>();
       for(int i:set){
         allGroupName.add(getNamePermissionGroup(i));
       }
       return allGroupName;
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

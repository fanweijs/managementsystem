package com.company.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @樊委
 * 2017-12-18
 */

public interface PermissionDAO {
    /**
     * 根据账号获取所权限项
     * @param account
     * @return
     * @throws SQLException
     */
    List<Integer> getPermissionItem(String account)throws SQLException;

    /**
     * 根据权限项的id获取权限组的id
     * @param a
     * @return
     * @throws SQLException
     */
    String getOnePermissionGroup(int a)throws SQLException;

    /**
  ID   * 根据权限项数组获取权限组集合
     * @param list
     * @return
     * @throws SQLException
     */
    Set<Integer>getAllPermissionGroup(List<Integer> list)throws SQLException;

    /**
     * 根据权限组id 获取名称
     * @param id
     * @return
     * @throws SQLException
     */
    String getNamePermissionGroup(int id)throws SQLException;

    /**
     * 根据权限项id获取 权限项名称
     * @param id
     * @return
     * @throws SQLException
     */
    String getNamePermissionItem(int id)throws SQLException;

    /**
     * 获取工号下的权限项 并分类到权限组下
     * @param account
     * @return
     * @throws SQLException
     */
    Map<String,List<String>> getGroupAndItem(String account)throws SQLException;
}

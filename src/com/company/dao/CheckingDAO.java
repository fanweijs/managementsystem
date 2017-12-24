package com.company.dao;

import com.company.model.Checking;
import com.company.model.Login;

import java.sql.SQLException;
import java.util.List;

/**
 * @author 咸赛
 *         2017/12/20
 */
public interface CheckingDAO {
    /**
     * 查询所有员工的考勤信息
     * @param
     * @return
     */
    List<Checking> getChecking() throws SQLException;

    /**
     * 根据工号查询某个员工信息
     * @return
     * @throws SQLException
     */
    List<Checking> get(String account)throws SQLException;

    /**
     * 得到所有员工账号
     * @return
     * @throws SQLException
     */
    List<String>getAllAccount()throws SQLException;

    /**
     * 新增一个考勤信息
     * @return
     * @throws SQLException
     */
    int insert(Login logins) throws SQLException;

    /**
     * 批量插入考勤信息
     * @param check_id
     * @return
     * @throws SQLException
     */
    int[] bachIntsert(List<Integer> check_id)throws SQLException;

    /**
     * 批量删除考勤信息
     * @param check_id
     * @return
     * @throws SQLException
     */
    int[] bachDelete(List<Integer> check_id) throws SQLException;

    /**
     * 按关键词查找考勤
     * @param keywords
     * @return
     * @throws SQLException
     */
    List<Checking>queryLike(String keywords)throws SQLException;

    /**
     * 按条件查找考勤
     * @param condition
     * @return
     * @throws SQLException
     */
    List<Checking>queryBy(String condition)throws SQLException;
}

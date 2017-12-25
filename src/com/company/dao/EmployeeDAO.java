package com.company.dao;

import com.company.model.Employee;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by 高展 on 2017/12/19.
 */
public interface EmployeeDAO {
    /**
     * 根据工号查找员工信息
     * @param account
     * @return
     * @throws SQLException
     */
    Employee get(String account)throws SQLException;

    /**
     * 修改员工信息
     */
    int update(Employee employee)throws SQLException;

    /**
     *新增一个员工信息
     */
    int insert(Employee employee)throws SQLException;

    /**
     * 删除一个员工
     */
    int deleteEmployee(String account)throws SQLException;

    /**
     * 获取所有员工信息
     */
    List<Employee>getAll()throws SQLException;

    /**
     * 关键字模糊查询员工信息
     */
    List<Employee> queryLike(String keywords)throws SQLException;


    int updateEmployee(Employee employee);
}

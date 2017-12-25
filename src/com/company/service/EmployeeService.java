package com.company.service;

import com.company.model.Employee;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by 高展 on 2017/12/20.
 */
public interface EmployeeService {
    /**
     * 添加员工信息
     */
    int insert(Employee employee)throws SQLException;

    /**
     * 获取员工信息
     */
    Employee getEmployee(String account);

    /**
     * 修改员工信息
     */
    int update(Employee employee) throws SQLException;


    /**
     * 获得所有员工信息
     * @return
     */
    List<Employee> getAll();

    /**
     * 关键字查找员工信息
     */
    List<Employee> queryLike(String keywords);
    int deleteOneEmployee(String account);

}

package com.company.dao;

import com.company.model.Salary;

import java.sql.SQLException;
import java.util.List;

/**
 *@author 樊委
 * 2017年12月25日10:46:46
 */
public interface SalaryDAO {
    /**
     * 发放月工资 有多少个员 就插入多少条数据
     * @return
     */
    List<Salary> batchInsert()throws SQLException;
}

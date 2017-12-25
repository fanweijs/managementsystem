package com.company.service;

import com.company.model.Salary;

import java.util.List;

/**
 * @author  樊委
 * 2017.12.23
 */

public interface FinancialAdminService {
    /**
     * 获取所有工资
     * @return
     */
    List<Salary> getAllSalary();

    /**
     * 模糊搜所
     * @param keywords
     * @return
     */
    List<Salary>qureyLikeSalary(String keywords);
    List<Salary> PayOff();
}

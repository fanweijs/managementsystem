package com.company.service;

import com.company.model.Employee;

import java.util.List;

/**
 * Created by 高展 on 2017/12/20.
 */
public interface EmployeeService {
    /**
     * 获取员工信息
     */
    Employee getEmployee(String account);

    /**
     * 修改员工信息
     */
    boolean updateEmployee(Employee employee);

    List<Employee> getAll();
}

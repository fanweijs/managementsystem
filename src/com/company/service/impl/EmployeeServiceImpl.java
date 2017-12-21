package com.company.service.impl;


import com.company.dao.EmployeeDAO;
import com.company.factory.DAOFactory;
import com.company.model.Employee;
import com.company.service.EmployeeService;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by 高展 on 2017/12/20.
 */
public class EmployeeServiceImpl implements EmployeeService {
    private EmployeeDAO employeeDAO= DAOFactory.getEmployeeDAOInstance();
    @Override
    public Employee getEmployee(String account) {
        Employee employee=null;
        try{
            employee=employeeDAO.get(account);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return employee;
    }

    @Override
    public boolean updateEmployee(Employee employee) {
        boolean flag=false;
        int n=0;
        try{
            n=employeeDAO.update(employee);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (n==1){
            flag=true;
        }
        return flag;
    }

    @Override
    public List<Employee> getAll() {
        List<Employee>employeeList=new ArrayList<>();
        try {
            employeeList=employeeDAO.getAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return employeeList;
    }
}

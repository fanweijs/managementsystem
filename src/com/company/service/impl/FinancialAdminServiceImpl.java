package com.company.service.impl;

import com.company.dao.SalaryBaseDAO;
import com.company.factory.DAOFactory;
import com.company.model.Salary;
import com.company.service.FinancialAdminService;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FinancialAdminServiceImpl implements FinancialAdminService {
    private SalaryBaseDAO salaryBaseDAO = DAOFactory.getSalaryBaseInstance();
    @Override
    public List<Salary> getAllSalary() {
        List<Salary>list = new ArrayList<>();
        try {
            list = salaryBaseDAO.getAllSalary();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public List<Salary> qureyLikeSalary(String keywords) {
        List<Salary>list = new ArrayList<>();
        try {
            list = salaryBaseDAO.queryLike(keywords);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
}

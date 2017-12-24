package com.company.service;

import com.company.model.Salary;

import java.util.List;

public interface FinancialAdminService {
    List<Salary> getAllSalary();
    List<Salary>qureyLikeSalary(String keywords);
}

package com.company.dao;

import com.company.dao.impl.SalaryBaseDAOImpl;
import com.company.model.Salary;
import org.junit.Test;

import java.sql.SQLException;
import java.util.List;

public class SalaryBaseDAOTest {
    private SalaryBaseDAO salaryBaseDAO = new SalaryBaseDAOImpl();
    @Test
    public void getCheckAllItem() {
        try {
            salaryBaseDAO.getCheckAllItem("1001");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void getPersonSalary() {
        try {
            List<Salary>list = salaryBaseDAO.getPersonSalary("1001");
        //    System.out.println(list);
            for(Salary salary:list){
                System.out.println(salary);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void getAllSalary() {
        try {
            List<Salary>list = salaryBaseDAO.getAllSalary();
            for (Salary salary :list){
                System.out.println(salary);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
package com.company.dao;

import com.company.dao.impl.EmployeeDAOImpl;
import com.company.model.Employee;
import org.junit.Test;

import java.sql.Date;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;


public class EmployeeDAOTest {
    private EmployeeDAO employeeDAO = new EmployeeDAOImpl();

    @Test
    public void insertEmployee() {
        byte[]bytes ={1,2};

        String time = "2017-11-28";
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        java.util.Date date =null;
        try {
            date =sdf.parse(time);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        java.util.Date utilDate = new java.util.Date();
        Date date1 = new Date(utilDate.getTime());

        Employee employee = new Employee("125",1,"fan",bytes,"男",date1,
                "文秘","已婚","党员","单转","1555","江苏","徐州",date1);

        try {
            employeeDAO.insert(employee);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
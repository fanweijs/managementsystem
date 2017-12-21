package com.company.service;


import com.company.factory.ServiceFactory;
import com.company.model.Employee;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

/**
 * Created by 高展 on 2017/12/21.
 */
public class EmployeeServiceTest {
    private EmployeeService employeeService;
    @Before
    public void setUp() throws Exception {
        employeeService= ServiceFactory.getEmployeeServiceInstance();
    }

    @Test
    public void getEmployee() throws Exception {
        List<Employee>list= (List<Employee>) employeeService.getEmployee("1001");
        list.forEach(employee -> System.out.println(employee));
    }

    @Test
    public void updateEmployee() throws Exception {

    }

    @Test
    public void getAll() throws Exception {
        List<Employee>list=employeeService.getAll();
        list.forEach(employee -> System.out.println(employee));
    }

}
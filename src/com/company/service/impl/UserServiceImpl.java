package com.company.service.impl;

import com.company.dao.EmployeeDAO;
import com.company.dao.LoginDAO;
import com.company.dao.PermissionDAO;
import com.company.dao.SalaryBaseDAO;
import com.company.factory.DAOFactory;
import com.company.frame.PermissionPanel;
import com.company.model.Employee;
import com.company.model.Login;
import com.company.model.Salary;
import com.company.service.UserService;
import sun.applet.Main;

import java.sql.SQLException;
import java.util.*;

public class UserServiceImpl implements UserService {
    private SalaryBaseDAO salaryBaseDAO = DAOFactory.getSalaryBaseInstance();
    private LoginDAO loginDAO = DAOFactory.getLoginDAOInstance();
    private PermissionDAO permissionDAO = DAOFactory.getPermissionInstance();
    private EmployeeDAO employeeDAO = DAOFactory.getEmployeeDAOInstance();

    @Override
    public List<Salary> getPersonSalary(String account) {
        List<Salary>list = new ArrayList<>();
        try {
            list = salaryBaseDAO.getPersonSalary(account);
        } catch (SQLException e) {
            e.printStackTrace();
        }
       return list;
    }


    @Override
    public Map<String, Object> adminLogin(String account, String password) {
        Map<String, Object> map = new HashMap<>();
        Login admin = null;
        try {
            admin = loginDAO.get(account);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        //找到了指定工号的管理员
        if (admin != null) {
            //比对密码
            if (admin.getPassword().equals(password)) {
                //登录成功
                map.put("info", "登录成功");
                map.put("admin", admin);
            } else {
                //工号对，密码不对
                map.put("info", "密码错误");
            }
        } else {
            map.put("info", "账号不存在");
        }
        return map;
    }

    @Override
    public List<String> userGetPermissionGroup(String account) {
        List<String>list = new ArrayList<>();
        try {
           list = permissionDAO.getAllGroupName(account);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public Map<String, List<String>> userGetPermissinItem(String account) {
        Map<String,List<String>>map  = new HashMap<>();
        try {
            map = permissionDAO.getGroupAndItem(account);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return map;
    }

    @Override
    public Employee getEmployee(String account) {
        Employee employee = new Employee();
        try {
            employee = employeeDAO.get(account);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return employee;
    }

}

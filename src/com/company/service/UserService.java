package com.company.service;

import com.company.model.Employee;
import com.company.model.Salary;

import java.util.List;
import java.util.Map;

/**
 * @author  樊委
 */

public interface UserService {
    /**
     * 查看个人薪资
     * @param account
     * @return
     */
    List<Salary> getPersonSalary(String account);

    /**
     * 登陆
     * @param account
     * @param password
     * @return
     */
    Map<String,Object> adminLogin(String account, String password);

    /**
     * 获取权限组
     * @param account
     * @return
     */
   List<String> userGetPermissionGroup(String account);

    /**
     * 获取权限项
     * @param account
     * @return
     */
    Map<String,List<String>> userGetPermissinItem(String account);

    /**
     * 获取用户
     * @param account
     * @return
     */
    Employee  getEmployee(String account);
}

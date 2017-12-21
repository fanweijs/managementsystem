package com.company.service.impl;

import com.company.dao.LoginDAO;
import com.company.factory.DAOFactory;
import com.company.model.Login;
import com.company.service.AdminService;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class AdminServiceImpl implements AdminService {
    private LoginDAO loginDAO = DAOFactory.getLoginDAOInstance();

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
}

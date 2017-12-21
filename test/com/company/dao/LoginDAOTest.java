package com.company.dao;


import com.company.factory.DAOFactory;
import com.company.model.Login;
import org.junit.Test;

import java.sql.SQLException;

public class LoginDAOTest {
    private LoginDAO loginDAO = DAOFactory.getLoginDAOInstance();
    @Test
    public void get() {
        String s ="1001";
        try {
           Login login = loginDAO.get(s);
            System.out.println(0%2);
            System.out.println(login);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
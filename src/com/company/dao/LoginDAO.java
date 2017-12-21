package com.company.dao;

import com.company.model.Login;

import java.sql.SQLException;

/**
 * @樊委
 * 2017-12-18
 */
public interface LoginDAO {
    Login get(String account)throws SQLException;
}

package com.company.dao.impl;

import com.company.dao.LoginDAO;
import com.company.model.Login;
import com.company.utils.JDBCUtil;

import java.sql.SQLException;
import java.util.Map;

/**
 * @author  樊委
 * 2017-12-18
 */
public class LoginDAOImpl implements LoginDAO {
    private JDBCUtil jdbcUtil = JDBCUtil.getInitJDBCUtil();
    @Override
    public Login get(String account) throws SQLException {
        String sql = "SELECT * FROM t_login WHERE login_account = ? ";
        Map<String, Object> map = jdbcUtil.executeQuerySingle(sql, new Object[]{account});
        if (map.size() != 0) {
            Login login = new Login(map.get("login_account").toString(),
                    map.get("password").toString()
            );
            return login;
        } else {
            return null;
        }
    }
}

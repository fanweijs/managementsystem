package com.company.dao;

import com.company.dao.impl.SalaryBaseDAOImpl;
import org.junit.Test;

import java.sql.SQLException;

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
}
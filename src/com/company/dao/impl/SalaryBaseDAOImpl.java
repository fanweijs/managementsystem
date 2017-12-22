package com.company.dao.impl;

import com.company.dao.SalaryBaseDAO;
import com.company.model.Salary;
import com.company.utils.JDBCUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;


public class SalaryBaseDAOImpl implements SalaryBaseDAO {
    private JDBCUtil jdbcUtil = JDBCUtil.getInitJDBCUtil();
    @Override
    public List<String> getBaseInfo(String account) throws SQLException {
        return null;
    }


    @Override
    public List<Integer> getCheckAllItem(String account) throws SQLException {
        Calendar cal = Calendar.getInstance();
        String year =null;
        String month=null;
        System.out.println(cal.get(Calendar.MONTH));
        //月份=0 即为1月
        if(cal.get(Calendar.MONTH) == 0){
            month="12";
            year= String.valueOf(cal.get(Calendar.YEAR)-1);
        }else {
            year = String.valueOf(cal.get(Calendar.YEAR));
            month =String.valueOf(cal.get(Calendar.MONTH));
        }

        return null;
    }

    @Override
    public List<Integer> getRpAllItem(String account) throws SQLException {
        return null;
    }

    @Override
    public List<Salary> getPersonSalary(String account) throws SQLException {
        String sql =" Select * from t_salary where account =?";
       List<Object> salaryList = jdbcUtil.excuteQuery(sql,new Object[]{account});
        return  getSalaryList(salaryList);
    }

    @Override
    public List<Salary> queryLike(String keywords) throws SQLException {
        String sql = "SELECT * FROM t_salary WHERE CONCAT(account,name) LIKE ? ";
        List<Object> list = jdbcUtil.excuteQuery(sql, new Object[]{"%" + keywords + "%"});
        return getSalaryList(list);
    }

    @Override
    public List<Salary> getAllSalary() throws SQLException {
        String sql = "SELECT * FROM t_salary ";
        List<Object> list = jdbcUtil.excuteQuery(sql, null);
        return getSalaryList(list);
    }

    public static List<Salary> getSalaryList(List<Object>salaryList){
        List<Salary>list = new ArrayList<>();
        for(Object object : salaryList){
            Map<String, Object> map = (Map<String, Object>) object;
            Salary salary = new Salary(map.get("account").toString(),
                    map.get("name").toString(),
                    (Date)map.get("sdate"),
                    (int)map.get("position_level"),
                    (double)map.get("basesalary"),
                    (double)map.get("levelsalary"),
            (double) map.get("allchecking"),
            (double)map.get("subsidy"),
            (double) map.get("ssalary"),
            (double)map.get("leavecut"),
            (double) map.get("selfinsurance"),
            (double)map.get("tax"),
            (double)map.get("tsalary"),
            map.get("flag").toString());
           list.add(salary);
        }
        return list;
    }
}

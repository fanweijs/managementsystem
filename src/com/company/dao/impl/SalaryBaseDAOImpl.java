package com.company.dao.impl;

import com.company.dao.SalaryBaseDAO;
import com.company.utils.JDBCUtil;

import java.sql.SQLException;
import java.util.Calendar;
import java.util.List;


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
        System.out.println(month);
        String timeString =year+"-"+month +"-31";
        String lowString =year+"-"+month +"-1";
        System.out.println(timeString);
        System.out.println(lowString);

        String sql ="SELECT rp_name FROM t_rp WHERE account = ? AND DATE_FORMAT(rp_time,'%Y-%m-%d')<=‘"+timeString+"’ AND DATE_FORMAT(rp_time,'%Y-%m-%d')>=‘"+lowString+"'";
       // PreparedStatement ps =connection.prepareStatement(sql);
        List<Object> list  =  jdbcUtil.excuteQuery(sql,new Object[]{account});
        for(Object o:list){
            System.out.println(o);
        }
        return null;
    }

    @Override
    public List<Integer> getRpAllItem(String account) throws SQLException {
        return null;
    }
}

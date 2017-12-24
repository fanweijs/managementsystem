package com.company.dao.impl;

import com.company.dao.SalaryBaseDAO;
import com.company.model.Info;
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
    public int[] getCheckAllItem(String account) throws SQLException {
        int[]a = new int[4];
       String time = getTime();
        String sql = "SELECT * FROM t_checking WHERE  account = ? AND datetime LIKE ? ";
        List<Object>list = jdbcUtil.excuteQuery(sql,new Object[]{account,time});
        int later =0;
        int chuchai =0;
        int qingjia = 0;
        int jiaban =0;
        for(Object object:list){
            Map<String, Object> map = (Map<String, Object>) object;
            if(map.get("condition").toString().equals("迟到")){
              later+=1;
            }
            if(map.get("condition").toString().equals("出差")){
              chuchai+=1;
            }
            if(map.get("condition").toString().equals("请假")){
              qingjia+=1;
            }
            if(map.get("condition").toString().equals("加班")){
              jiaban+=1;
            }
        }
        a[0]=later;
        a[1]=chuchai;
        a[2]=qingjia;
        a[3]=jiaban;
        return a;
    }

    @Override
    public int []getRpAllItem(String account) throws SQLException {
        String time = getTime();
        int[]a = new int[]{0,0,0,0};

        String sql ="SELECT * FROM t_rp WHERE  account = ? AND rp_time LIKE ? ";
        List<Object>list = jdbcUtil.excuteQuery(sql,new Object[]{account,time});
        for(Object object :list){
            Map<String, Object> map = (Map<String, Object>) object;
            String name =map.get("rp_name").toString();
            if("优秀员工".equals(name)){
                a[0]+=1;
            }
            if("先进个人".equals(name)){
                a[1]+=1;
            }
            if("先进团队".equals(name)){
                a[2]+=1;
            }
            if("全勤奖".equals(name)){
                a[3]+=1;
            }
        }

        return a;
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
    public static String getTime(){
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
        String time=year+"-"+month+"-%";
        return time;

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

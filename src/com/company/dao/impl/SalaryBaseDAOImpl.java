package com.company.dao.impl;

import com.company.dao.SalaryBaseDAO;
import com.company.factory.ServiceFactory;
import com.company.model.Employee;
import com.company.model.Salary;
import com.company.service.EmployeeService;
import com.company.utils.JDBCUtil;

import java.sql.*;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author 樊委
 */
public class SalaryBaseDAOImpl implements SalaryBaseDAO {
    private JDBCUtil jdbcUtil = JDBCUtil.getInitJDBCUtil();
    private EmployeeService employeeService = ServiceFactory.getEmployeeServiceInstance();
    @Override
    public Map<String,Integer> getBaseInfo(String account) throws SQLException {
        Map<String, Integer> map = new HashMap<>();
        Employee employee = employeeService.getEmployee(account);
        java.util.Date utilDate = new java.util.Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy");
        int nowYear = Integer.valueOf(simpleDateFormat.format(utilDate));
        Date nowTime = new Date(utilDate.getTime());
        Date sqlDate = employee.getDate();
        int beforeTime =  Integer.valueOf(simpleDateFormat.format(sqlDate));
        int workTime = nowYear-beforeTime;
        int positionLevel = workTime%2;
        if(employee.getPosition().contains("人事部管理员")){
           map.put("基本工资",8500);
        } else if(employee.getPosition().contains("财务部管理员")){
            map.put("基本工资",8000);
        }else {
            map.put("基本工资",4500);
        }
        map.put("时间",workTime);
        map.put("等级",positionLevel);
        return map;
    }


    @Override
    public Map<String,Integer> getCheckAllItem(String account) throws SQLException {
        Map<String,Integer>integerMap = new HashMap<>();
       String time = getTime();
        String sql = "SELECT * FROM t_checking WHERE  account = ? AND datetime LIKE ? ";
        List<Object>list = jdbcUtil.excuteQuery(sql,new Object[]{account,time});
        int later =0;
        int chuchai =0;
        int qingjia = 0;
        int jiaban =0;
        int kuangong=0;
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
            if (map.get("condition").toString().equals("旷工")){
                kuangong+=1;
            }
        }
        integerMap.put("迟到",later);
        integerMap.put("出差",chuchai);
        integerMap.put("请假",qingjia);
        integerMap.put("加班",jiaban);
        integerMap.put("旷工",kuangong);
        return integerMap;
    }

    @Override
    public Map<String,Integer>getRpAllItem(String account) throws SQLException {
        Map<String,Integer>integerMap = new HashMap<>();
        String time = getTime();
        int[]a = new int[]{0,0,0,0,0,0};

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
            if("多次迟到".equals(name)){
                a[3]+=1;
            }
            if("违纪违规".equals(name)){
                a[4]+=1;
            }
            if("全勤奖".equals(name)){
                a[5]=1;
            }
        }
        integerMap.put("优秀员工",a[0]);
        integerMap.put("先进个人",a[1]);
        integerMap.put("优秀团队",a[2]);
        integerMap.put("多次迟到",a[3]);
        integerMap.put("违纪违规",a[4]);
        integerMap.put("全勤奖",a[5]);
        return integerMap;
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

    @Override
    public int insertSalary(Salary salary) throws SQLException {
        String sql="INSERT INTO t_salary VALUES(null,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        Object[] params={salary.getAccount(),salary.getSdate(),salary.getPoistion_level(),
                salary.getBaseSalary(),salary.getLevelSalary(), salary.getAllChecking(),salary.getSubsidy(),
                salary.getsSalary(),salary.getLeaveCut(),salary.getSelfInsurance(),salary.getTax(),
                salary.gettSalary(),salary.getFlag()};
        int n=jdbcUtil.executeUpdate(sql,params);
        return n;
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

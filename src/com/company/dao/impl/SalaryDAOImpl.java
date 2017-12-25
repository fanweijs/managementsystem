package com.company.dao.impl;

import com.company.dao.SalaryBaseDAO;
import com.company.dao.SalaryDAO;
import com.company.factory.DAOFactory;
import com.company.factory.ServiceFactory;
import com.company.model.Employee;
import com.company.model.Salary;
import com.company.service.EmployeeService;

import java.sql.SQLException;
import java.util.*;

/**
 * @author 樊委
 * 2017年12月25日10:49:15
 */
public class SalaryDAOImpl implements SalaryDAO{
    private EmployeeService employeeService = ServiceFactory.getEmployeeServiceInstance();
    private SalaryBaseDAO salaryBaseDAO = DAOFactory.getSalaryBaseInstance();
    @Override
    public List<Salary> batchInsert()throws SQLException {
        List<Salary>list = new ArrayList<>();
        List<Employee> employees = employeeService.getAll();
        for (Employee employee : employees) {
            try {
                Map<String, Integer> baseMap = salaryBaseDAO.getBaseInfo(employee.getAccount());
                Map<String, Integer> checkMap = salaryBaseDAO.getCheckAllItem(employee.getAccount());
                Map<String, Integer> rpMap = salaryBaseDAO.getRpAllItem(employee.getAccount());
                int poistion_level = baseMap.get("等级");
                double baseSalary = baseMap.get("基本工资");
                double levelSalary =poistion_level*500;
                double allChecking = rpMap.get("全勤奖")*500;
                double subsidy = checkMap.get("出差")*400+checkMap.get("加班")*200;
                double sSalary = baseSalary+levelSalary+allChecking+subsidy;
                double leaveCut =checkMap.get("请假")*200 + checkMap.get("旷工")*400;
                double selfInsurance = sSalary*0.8;
                double a = sSalary -3500;
                double tax=0.0;
                double[]taxt = new double[]{0.0,0.,0.,0.,0.,0.,0.};
                if( a<=1500){
                    taxt[0]= sSalary*0.03-0;
                }
                if( a>1500&&a<=4500){
                    taxt[1]= sSalary*0.1-105;
                }
                if( a>4500&&a<=9000){
                    taxt[2]= sSalary*0.2-555;
                }
                if( a>9000&&a<=35000){
                    taxt[3]= sSalary*0.25-1005;
                }
                if( a>35000&&a<=55000){
                    taxt[4]= sSalary*0.3-2755;
                }
                if( a>55000&&a<=80000){
                    taxt[5]= sSalary*0.35-5505;
                }
                if( a>80000){
                    taxt[6]= sSalary*0.45-13505;
                }
                for(double d :taxt){
                    tax+=d;
                }
                double tSalary = sSalary -selfInsurance -leaveCut-tax;
                String flag  = "未发";
                java.util.Date date = new Date();
                java.sql.Date sqlDate= new java.sql.Date(date.getTime());
               Salary salary =  new Salary(employee.getAccount(),sqlDate,poistion_level,baseSalary,levelSalary,allChecking,subsidy,sSalary,leaveCut,selfInsurance,tax,tSalary,flag);
               salaryBaseDAO.insertSalary(salary);
               list.add(salary);

            } catch (SQLException e) {
                e.printStackTrace();
            }

        }
      return list;

    }
}


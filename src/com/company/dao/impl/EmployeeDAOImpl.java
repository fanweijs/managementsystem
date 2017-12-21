package com.company.dao.impl;

import com.company.dao.EmployeeDAO;
import com.company.model.Employee;
import com.company.utils.JDBCUtil;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by 高展 on 2017/12/19.
 */
public class EmployeeDAOImpl implements EmployeeDAO {
    private JDBCUtil jdbcUtil= JDBCUtil.getInitJDBCUtil();

    //根据工号查找员工信息
    @Override
    public Employee get(String account) throws SQLException {
        String sql="SELECT*FROM t_employee WHERE account=?";
        Map<String,Object>map=jdbcUtil.executeQuerySingle(sql,new Object[]{account});
        if (map.size()!=0){
            Employee employee=new Employee(map.get("account").toString(),
                                           (Integer)map.get("departmentID"),
                                           map.get("name").toString(),
                                           (byte[])map.get("avatar"),
                                           map.get("sex").toString(),
                                          (Date)map.get("birthday"),
                                           map.get("position").toString(),
                                           map.get("maritalstatus").toString(),
                                           map.get("politiacal_status").toString(),
                                           map.get("education").toString(),
                                           map.get("phone").toString(),
                                           map.get("native_province").toString(),
                                           map.get("native_city").toString(),
                                           (Date) map.get("date"));
            return employee;
        }else {
            return null;
        }
    }

    //修改员工信息
    @Override
    public int update(Employee employee) throws SQLException {
        String sql="SELECT t_employee SET departmentID=?,name=?,avatar=?,sex=?,birthday=?,position=?,maritalstatus=?,politiacal_status," +
                "education=?,phone=?,native_province=?,native_city=?,date=?";
        Object[] params={employee.getDepartmentID(),
                         employee.getName(),
                         employee.getAvatar(),
                         employee.getSex(),
                         employee.getBirthday(),
                         employee.getPosition(),
                         employee.getMaritalstatus(),
                         employee.getPolitiacal_status(),
                         employee.getEducation(),
                         employee.getPhone(),
                         employee.getNative_province(),
                         employee.getNative_city(),
                         employee.getDate()};
        int n=jdbcUtil.executeUpdate(sql,params);
        return n;
    }

    //添加员工信息
    @Override
    public int insert(Employee employee) throws SQLException {
        String sql="INSERT INTO t_employee VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        Object[] params={employee.getAccount(),
                         employee.getDepartmentID(),
                         employee.getName(),
                         employee.getAvatar(),
                         employee.getSex(),
                         employee.getBirthday(),
                         employee.getPosition(),
                         employee.getMaritalstatus(),
                         employee.getPolitiacal_status(),
                         employee.getEducation(),
                         employee.getPhone(),
                         employee.getNative_province(),
                         employee.getNative_city(),
                         employee.getDate()};
        int n=jdbcUtil.executeUpdate(sql,params);
        return n;
    }

    //删除员工信息
    @Override
    public int delete(int account) throws SQLException {
        String sql="SELECT*FROM t_employee WHERE account=?";
        Object[] params={account};
        int n=jdbcUtil.executeUpdate(sql,params);
        return n;
    }


    //获取所有员工信息
    @Override
    public List<Employee> getAll() throws SQLException {
        String sql="SELECT*FROM t_employee";
        List<Object>list=jdbcUtil.excuteQuery(sql,null);
        return getEmployeeList(list);
    }

    //关键字模糊查找员工信息
    @Override
    public List<Employee> queryLike(String keywords) throws SQLException {
        String sql="SELECT*FROM t_employee WHERE CONCAT(account,name,sex,position,maritalstatus,politiacal_status,education)LIKE ?";
        List<Object>list=jdbcUtil.excuteQuery(sql,new Object[]{"%"+keywords+"%"});
        return getEmployeeList(list);
    }

    //按条件查找员工信息
    @Override
    public List<Employee> queryBy(String condition) throws SQLException {
        String sql="SELECT*FROM t_employee"+condition;
        System.out.println(sql);
        List<Object>list=jdbcUtil.excuteQuery(sql,null);
        return getEmployeeList(list);
    }

    @Override
    public int updateEmployee(Employee employee) {
        String sql="SELECT t_employee SET departmentID=?,name=?,avatar=?,sex=?,birthday=?,position=?,maritalstatus=?,politiacal_status," +
                "education=?,phone=?,native_province=?,native_city=?,date=?";
        Object[] params={employee.getDepartmentID(),
                employee.getName(),
                employee.getAvatar(),
                employee.getSex(),
                employee.getBirthday(),
                employee.getPosition(),
                employee.getMaritalstatus(),
                employee.getPolitiacal_status(),
                employee.getEducation(),
                employee.getPhone(),
                employee.getNative_province(),
                employee.getNative_city(),
                employee.getDate()};
        int n=jdbcUtil.executeUpdate(sql,params);
        return n;
    }

    //封装一个本类的私有方法，用object集合转换成student类型集合
    private List<Employee>getEmployeeList(List<Object>list){
        List<Employee>employees=new ArrayList<>();
        for (Object object:list){
            Map<String ,Object>map=(Map<String,Object>) object;
            Employee employee=new Employee(map.get("account").toString(),
                                           (Integer)map.get("departmentID"),
                                           map.get("name").toString(),
                                           (byte[])map.get("avatar"),
                                           map.get("sex").toString(),
                                           (Date)map.get("birthday"),
                                           map.get("position").toString(),
                                           map.get("maritalstatus").toString(),
                                           map.get("politiacal_status").toString(),
                                           map.get("education").toString(),
                                           map.get("phone").toString(),
                                           map.get("native_province").toString(),
                                           map.get("native_city").toString(),
                                          (Date) map.get("date"));
            employees.add(employee);
        }
        return employees;
    }
}

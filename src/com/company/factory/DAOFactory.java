package com.company.factory;

import com.company.dao.*;
import com.company.dao.impl.*;
import com.company.model.Salary;

public class DAOFactory {
    public static LoginDAO getLoginDAOInstance(){
        return  new LoginDAOImpl();
    }
    public static EmployeeDAO getEmployeeDAOInstance(){
        return new EmployeeDAOImpl();
    }
    public static CheckingDAO getCheckingDAOInstance(){
        return new CheckingDAOImpl();
    }
    public static RewardPublishDAO getRewardPublishInstance(){
        return new RewardPublishDAOImpl();
    }
    public static InfoDAO getInfoDAOInstance() {
        return new InfoDAOImpl();
    }
    public static SalaryBaseDAO getSalaryBaseInstance(){
        return  new SalaryBaseDAOImpl();
    }
    public  static PermissionDAO getPermissionInstance(){
        return  new PermissionDAOImpl();
    }
    public static SalaryDAO getSalaryDAOInstance(){
        return new SalaryDAOImpl();
    }


}

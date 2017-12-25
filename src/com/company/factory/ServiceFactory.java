package com.company.factory;

import com.company.service.*;
import com.company.service.impl.*;

public class ServiceFactory {
    public static EmployeeService getEmployeeServiceInstance(){
        return new EmployeeServiceImpl();
    }
    public static CheckingService getCheckingServiceInstance(){
        return new CheckingServiceImpl();
    }
    public static RpService getRpServiceInstance(){
        return new RpServiceImpl();
    }

    public static UserService getUserSerivceInstance(){
        return new UserServiceImpl();
    }
    public static FinancialAdminService getFinanicalAdminInstance(){
        return  new FinancialAdminServiceImpl();
    }
    public static InfoService getInfoServiceInstance(){
        return  new InfoServiceImpl();
    }

}

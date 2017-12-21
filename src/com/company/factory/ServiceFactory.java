package com.company.factory;

import com.company.service.AdminService;
import com.company.service.CheckingService;
import com.company.service.EmployeeService;
import com.company.service.RpService;
import com.company.service.impl.AdminServiceImpl;
import com.company.service.impl.CheckingServiceImpl;
import com.company.service.impl.EmployeeServiceImpl;
import com.company.service.impl.RpServiceImpl;

public class ServiceFactory {
    public static AdminService getAdminSericeInstance(){
        return new AdminServiceImpl();
    }
    public static EmployeeService getEmployeeServiceInstance(){
        return new EmployeeServiceImpl();
    }
    public static CheckingService getCheckingServiceInstance(){
        return new CheckingServiceImpl();
    }
    public static RpService getRpServiceInstance(){
        return new RpServiceImpl();
    }

}

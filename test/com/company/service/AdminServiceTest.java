package com.company.service;

import com.company.factory.ServiceFactory;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

public class AdminServiceTest {
    private AdminService adminService = ServiceFactory.getAdminSericeInstance();
    @Test
    public void adminLogin() {
        Map<String ,Object>map = new HashMap<>();
         map  =adminService.adminLogin("1001","111");
     //    map.get("info")
        System.out.println(map.get("info"));
    }
}
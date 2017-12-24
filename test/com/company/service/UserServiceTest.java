package com.company.service;

import com.company.factory.ServiceFactory;
import org.junit.Test;

import java.util.List;
import java.util.Map;


public class UserServiceTest {
    private UserService userService  = ServiceFactory.getUserSerivceInstance();

    @Test
    public void userGetPermissionGroup() {
        List<String>list =  userService.userGetPermissionGroup("1004");
        System.out.println(list);

    }

    @Test
    public void userGetPermissinItem() {
    }

    @Test
    public void adminLogin() {
        Map<String,Object> map =  userService.adminLogin("1001","111");
        System.out.println(map.get("info"));
        System.out.println(map.get("admin"));

    }
}
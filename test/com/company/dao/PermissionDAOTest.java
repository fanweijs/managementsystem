package com.company.dao;

import com.company.dao.impl.PermissionDAOImpl;
import org.junit.Test;

import java.sql.SQLException;
import java.util.*;

public class PermissionDAOTest {
private PermissionDAO permissionDAO =  new PermissionDAOImpl();
    @Test
    public void getPermissionItem() {
        try {
            List<Integer>list = permissionDAO.getPermissionItem("1001");
            list.forEach(integer -> System.out.println(integer));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void getOnePermissionGroup() {
        try {
           String a = permissionDAO.getOnePermissionGroup(8);
            System.out.println(a);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void getAllPermissionGroup() {
        try {
            List<Integer>list = permissionDAO.getPermissionItem("1001");
          //  list.forEach(integer -> System.out.println(integer));
            Set<Integer> set= new HashSet<>();
            set=permissionDAO.getAllPermissionGroup(list);
            set.forEach(integer -> System.out.println(integer));
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Test
    public void getNamePermissionGroup() {
        try {
            String a = permissionDAO.getNamePermissionGroup(1);
            System.out.println(a);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void getNamePermissionItem() {

        try {
            String a = permissionDAO.getNamePermissionItem(1);
            System.out.println(a);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void getGroupAndItem() {
        Map<String,List<String>> map = new HashMap<>();
        try {
            map = permissionDAO.getGroupAndItem("1001");
            map.forEach(((s, strings) -> System.out.println(s+strings)));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        List<String> list =map.get("档案");
        System.out.println(list);

    }

    @Test
    public void getAllGroupName() {
        try {
            List<String>list = permissionDAO.getAllGroupName("1001");
            System.out.println(list);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
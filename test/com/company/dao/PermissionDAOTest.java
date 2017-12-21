package com.company.dao;

import com.company.dao.impl.PermissionDAOImpl;
import org.junit.Test;

import java.sql.SQLException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
}
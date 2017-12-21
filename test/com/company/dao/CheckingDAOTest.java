package com.company.dao;

import com.company.dao.impl.CheckingDAOImpl;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by asus on 2017/12/21.
 */
public class CheckingDAOTest {
    private CheckingDAO checkingDAO=new CheckingDAOImpl();

    @org.junit.Before
    public void setUp() throws Exception {

    }

    @org.junit.Test
    public void getChecking() throws Exception {

    }

    @org.junit.Test
    public void getAll() throws Exception {

    }

    @org.junit.Test
    public void getAllAccount() throws Exception {
        List<String>list =new ArrayList<>();
                list=checkingDAO.getAllAccount();
        System.out.println(list);
    }

    @org.junit.Test
    public void insert() throws Exception {

    }

    @org.junit.Test
    public void bachIntsert() throws Exception {

    }

    @org.junit.Test
    public void bachDelete() throws Exception {

    }

}
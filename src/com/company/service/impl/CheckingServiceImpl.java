package com.company.service.impl;

import com.company.dao.CheckingDAO;
import com.company.factory.DAOFactory;
import com.company.model.Checking;
import com.company.model.Login;
import com.company.service.CheckingService;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by asus on 2017/12/20.
 */
public class CheckingServiceImpl implements CheckingService{
    private CheckingDAO checkingDAO = DAOFactory.getCheckingDAOInstance();

    @Override
    public List<Checking> getChecking() {
        List<Checking> checkingList = new ArrayList<>();
        try {
            checkingList = checkingDAO.getChecking();
        }catch (SQLException e){
            e.printStackTrace();
        }
        return checkingList;
    }

    @Override
    public List<Checking> get(String account)  {
      List<Checking> list = new ArrayList<>();
        try {
            list = checkingDAO.get("1001");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public List<String> getAllAccount() {
        List<String> CheckingList = null;
        try {
            CheckingList = checkingDAO.getAllAccount();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return CheckingList;
    }

    @Override
    public int insert(Login logins) {
        int n = 0;
        try {
            n = checkingDAO.insert(logins);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return n;
    }

    @Override
    public int[] bachIntsert(List<Integer> check_id) {
        int[] result = new int[check_id.size()];
        try {
            result = checkingDAO.bachIntsert(check_id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public int[] bachDelete(List<Integer> check_id) {
        int[] result = new int[check_id.size()];
        try {
            result = checkingDAO.bachDelete(check_id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public List<Checking> queryLike(String keywords) {
        List<Checking> checkingList = new ArrayList<>();
        try {
            checkingList = checkingDAO.queryLike(keywords);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return checkingList;
    }

    @Override
    public List<Checking> queryBy(String condition) {
        List<Checking>checkingList = new ArrayList<>();
        try {
            checkingList = checkingDAO.queryBy(condition);
        }catch (SQLException e){
            e.printStackTrace();
        }
        return checkingList;
    }
}

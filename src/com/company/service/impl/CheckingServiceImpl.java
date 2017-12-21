package com.company.service.impl;

import com.company.dao.CheckingDAO;
import com.company.factory.DAOFactory;
import com.company.model.Checking;
import com.company.model.Login;
import com.company.service.CheckingService;

import java.util.List;

/**
 * Created by asus on 2017/12/20.
 */
public class CheckingServiceImpl implements CheckingService {
    private CheckingDAO checkingDAO = DAOFactory.getCheckingDAOInstance();

    @Override
    public List<Checking> getChecking(String check_id) {
        return null;
    }

    @Override
    public List<Checking> getAll() {
        return null;
    }

    @Override
    public List<String> getAllAccount() {
        return null;
    }

    @Override
    public int insert(Login logins) {
        return 0;
    }

    @Override
    public int[] bachIntsert(List<String> list) {
        return new int[0];
    }

    @Override
    public int[] bachDelete(List<Integer> check_id) {
        return new int[0];
    }
}

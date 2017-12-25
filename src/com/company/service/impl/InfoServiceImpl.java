package com.company.service.impl;

import com.company.dao.InfoDAO;
import com.company.factory.DAOFactory;
import com.company.model.Info;
import com.company.service.InfoService;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by lihongyu on 2017/12/20.
 */
public class InfoServiceImpl implements InfoService {
    private InfoDAO infoDAO = DAOFactory.getInfoDAOInstance();

    @Override
    public List<Info> getAll() {
        List<Info> infoList = new ArrayList<>();
        try {
            infoList = infoDAO.getAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return infoList;
    }

    @Override
    public int insert(Info info) {
        int n = 0;
        try {
            n = infoDAO.insert(info);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return n;
    }
}

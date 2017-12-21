package com.company.dao;

import com.company.factory.DAOFactory;
import com.company.model.Info;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

/**
 * Created by lihongyu on 2017/12/20.
 */
public class InfoDAOTest {
    private InfoDAO infoDAO;
    @Before
    public void setUp() throws Exception {
        infoDAO = DAOFactory.getInfoDAOInstance();
    }

    @Test
    public void getAll() throws Exception {
        List<Info>list=infoDAO.getAll();
        list.forEach(info -> System.out.println(info));
    }
}
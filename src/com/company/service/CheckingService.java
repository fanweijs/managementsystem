package com.company.service;


import com.company.model.Checking;
import com.company.model.Login;

import java.util.List;

/**
 * Created by asus on 2017/12/21.
 */
public interface CheckingService {

    List<Checking> getChecking(String check_id);

    List<Checking> getAll();

    List<String>getAllAccount();

    int insert(Login logins);

    int[] bachIntsert(List<String> list);

    int[] bachDelete(List<Integer> check_id);
}

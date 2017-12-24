package com.company.service;

import com.company.model.Checking;
import com.company.model.Login;

import java.util.List;

/**
 * Created by asus on 2017/12/21.
 */
public interface CheckingService {
//    /**
//     * 登录
//     * @param login_account
//     * @param password
//     * @return
//     */
//    Map<String,Object>Login(String login_account, String password);

    List<Checking> getChecking();

    List<Checking> get(String account);

    List<String>getAllAccount();

    int insert(Login logins);

    int[] bachIntsert(List<Integer> check_id);

    int[] bachDelete(List<Integer> check_id);

    List<Checking> queryLike(String keywords);

    List<Checking> queryBy(String condition);
}

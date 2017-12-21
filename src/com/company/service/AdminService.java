package com.company.service;

import java.util.Map;

/**
 * @author 樊委
 */
public interface AdminService {
    Map<String,Object> adminLogin(String account, String password);

}

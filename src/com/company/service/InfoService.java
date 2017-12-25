package com.company.service;

import com.company.model.Info;

import java.util.List;

/**
 * Created by lihongyu on 2017/12/20.
 */
public interface InfoService {
    /**
     * 以下方法为通知管理模块功能
     */
    List<Info> getAll();

    int insert(Info info);
}

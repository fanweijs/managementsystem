package com.company.dao;

import com.company.model.Info;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by 李宏宇 on 2017/12/19.
 */
public interface InfoDAO {
    /**
     * 获取所有通知信息
     *
     * @return list
     * @throws SQLException
     */
    List<Info> getAll() throws SQLException;
    /**
     * 新增一个通知信息
     * @param info
     * @return int
     * @throws SQLException
     */
    int insert(Info info) throws SQLException;

}

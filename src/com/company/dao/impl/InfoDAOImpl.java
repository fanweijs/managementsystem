package com.company.dao.impl;

import com.company.dao.InfoDAO;
import com.company.model.Info;
import com.company.utils.JDBCUtil;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by 李宏宇 on 2017/12/20.
 */
public class InfoDAOImpl implements InfoDAO {
    private JDBCUtil jdbcUtil = JDBCUtil.getInitJDBCUtil();

    @Override
    public List<Info> getAll() throws SQLException {
        String sql = "SELECT * FROM t_info";
        List<Object> list = jdbcUtil.excuteQuery(sql, null);
        return getInfoList(list);
    }

    @Override
    public int insert(Info info) throws SQLException {
        String sql = "INSERT INTO t_info VALUES (?,?,?,?,?) ";
        Object[] params = {info.getId(), info.getInfotime(), info.getTitle(),
                info.getContent(), info.getDepartment()};
        int n = jdbcUtil.executeUpdate(sql, params);
        return n;
    }

    /**
     * 封装一个本类的私有方法，用来把Object集合转换成Info类型的集合
     *
     * @param list
     * @return
     */
    private List<Info> getInfoList(List<Object> list) {
        List<Info> infos = new ArrayList<>();
        for (Object object : list) {
            Map<String, Object> map = (Map<String, Object>) object;
            Info info = new Info((Integer)map.get("id"),
                                 (Date) map.get("infotime"),
                                 map.get("title").toString(),
                                 map.get("content").toString(),
                                (Integer)map.get("department"));
            infos.add(info);
        }
        return infos;
    }
}


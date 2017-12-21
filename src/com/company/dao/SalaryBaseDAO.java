package com.company.dao;

import java.sql.SQLException;
import java.util.List;

/**
 * @author  樊委
 * time 2017-12-20
 */

public interface SalaryBaseDAO {
    /**
     * 读取个人档案部分信息
     * 工资发放依据
     * 工龄 现在时间 - 入职时间  每工龄 200 放在补贴里
     * 职位等级 = 工龄%2 500
     * 职位 人事部管理员 8500
     *      财务部管理员 8400
     *      普通员工 4500 有钱任性
     *
     * @param account
     * @return
     * @throws SQLException
     */
    List<String> getBaseInfo(String account)throws SQLException;

    /**
     * 统计各项考勤种类对应次数
     * 读取考勤表部分信息
     * 顺序如下
     * 迟到 / 天   -100
     * 加班 / 天   +200
     * 出差 /天 + 400
     * 请假 /天 -20
     * 旷工/天 -400
     * @param account
     * @return
     * @throws SQLException
     */
    List<Integer>getCheckAllItem(String account)throws SQLException;
    /**
     *
     * 得到各项奖惩及次数 顺序如下
     * 读取奖惩表部分信息
     * 奖
     * 先进个人
     * 明星员工
     * 全勤奖
     * 先进团队
     *
     * 违纪违规
     * 多次迟到
     *
     */
    List<Integer>getRpAllItem(String account)throws SQLException;

}

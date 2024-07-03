package com.netease.cloud.nsf.demo.stock.advisor.web.database;

import java.sql.Connection;

/**
 * @author: SUN_Huang (huangyang06@corp.netease.com)
 * @createTime: 2022/11/14 10:13
 */
public interface IConnectionService {

    /**
     * 获取连接
     *
     * @return 数据库连接
     */
    Connection getConn();

}

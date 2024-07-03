package com.netease.cloud.nsf.demo.stock.advisor.web.database.impl;

import com.netease.cloud.nsf.demo.stock.advisor.web.database.IConnectionService;
import com.netease.cloud.nsf.demo.stock.advisor.web.database.NsfDatabaseConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * @author: SUN_Huang (huangyang06@corp.netease.com)
 * @createTime: 2022/11/14 10:20
 */
@Service
public class JdbcConnectionServiceImpl implements IConnectionService {

    private final static Logger log = LoggerFactory.getLogger(JdbcConnectionServiceImpl.class);

    @Autowired
    private NsfDatabaseConfiguration databaseConfiguration;

    @Override
    public Connection getConn(){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            return DriverManager.getConnection(databaseConfiguration.getUrl(),databaseConfiguration.getUsername(),databaseConfiguration.getPassword());
        } catch (ClassNotFoundException | SQLException e) {
//            log.error("SQL_Exception: {}", e.getMessage(), e);
            throw new RuntimeException(e);
        }
    }
}

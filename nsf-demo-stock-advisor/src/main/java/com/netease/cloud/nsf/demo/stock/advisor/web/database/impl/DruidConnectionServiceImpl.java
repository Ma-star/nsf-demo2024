package com.netease.cloud.nsf.demo.stock.advisor.web.database.impl;

import com.alibaba.druid.pool.DruidDataSource;
import com.netease.cloud.nsf.demo.stock.advisor.web.database.IConnectionService;
import com.netease.cloud.nsf.demo.stock.advisor.web.database.NsfDatabaseConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * @author: SUN_Huang (huangyang06@corp.netease.com)
 * @createTime: 2022/11/8 11:06
 */
//@Service
public class DruidConnectionServiceImpl implements IConnectionService {

    private final static Logger log = LoggerFactory.getLogger(DruidConnectionServiceImpl.class);

    @Autowired
    private NsfDatabaseConfiguration databaseConfiguration;

    private volatile DruidDataSource dataSource = null;

    private void init() {

        dataSource = new DruidDataSource();

        dataSource.setUrl(databaseConfiguration.getUrl());
        dataSource.setUsername(databaseConfiguration.getUsername());
        dataSource.setPassword(databaseConfiguration.getPassword());

        // 申请连接的时候检测，如果空闲时间大于timeBetweenEvictionRunsMillis，执行validationQuery检测连接是否有效。
        dataSource.setTestWhileIdle(true);
        // 申请连接时检测连接是否有效
        dataSource.setTestOnBorrow(false);
        // 归还连接时检测连接是否有效
        dataSource.setTestOnReturn(false);
        dataSource.setRemoveAbandoned(true);
        // 是否缓存preparedStatement，也就是PSCache。PSCache对支持游标的数据库性能提升巨大，比如说oracle。在mysql下建议关闭。
        dataSource.setPoolPreparedStatements(false);
        dataSource.setValidationQuery("select 1");
        // 最大连接池数量
        dataSource.setMaxActive(50);
        // 最小连接池数量
        dataSource.setMinIdle(20);
        // 初始化连接数
        dataSource.setInitialSize(20);
        // 获取连接最大等待时间，缺省使用公平锁，会降低并发效率（可手动开启非公平锁）
        dataSource.setMaxWait(30000);
        // 使用非公平锁
        dataSource.setUseUnfairLock(true);
        dataSource.setTimeBetweenConnectErrorMillis(60000);
        dataSource.setMaxPoolPreparedStatementPerConnectionSize(20);
    }

    @Override
    public Connection getConn() {
        if (null == dataSource) {
            synchronized (DruidConnectionServiceImpl.class) {
                if (null == dataSource) {
                    init();
                }
            }
        }
        try {
            return dataSource.getConnection();
        } catch (SQLException e) {
            log.error("获取连接失败, 异常信息: {}", e.getMessage(), e);
            throw new RuntimeException(e);
        }
    }
}

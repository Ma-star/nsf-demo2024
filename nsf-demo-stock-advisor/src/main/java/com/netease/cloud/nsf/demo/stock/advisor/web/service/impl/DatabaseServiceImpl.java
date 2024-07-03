package com.netease.cloud.nsf.demo.stock.advisor.web.service.impl;

import com.netease.cloud.nsf.demo.stock.advisor.web.database.IConnectionService;
import com.netease.cloud.nsf.demo.stock.advisor.web.entity.DemoAdvisor;
import com.netease.cloud.nsf.demo.stock.advisor.web.service.IDatabaseService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;

/**
 * @author: SUN_Huang (huangyang06@corp.netease.com)
 * @createTime: 2022/11/8 14:28
 */
@Service
public class DatabaseServiceImpl implements IDatabaseService {

    private final static Logger log = LoggerFactory.getLogger(DatabaseServiceImpl.class);

    private final static String INSERT_ADVISOR_SQL = "INSERT INTO demo_advisor (name, rate) values (?, ?);";
    private final static String INSERT_ADVISOR_SQL_1 = "INSERT INTO demo_advisor (name, rate, create_time) values (?, ?, ?);";
    private final static String SELECT_ADVISOR_SQL = "SELECT * FROM demo_advisor ORDER BY create_time desc limit 1;";

    @Autowired
    private IConnectionService connectionService;

    @Override
    public void insertAdvisor(DemoAdvisor demoAdvisor) {

        final String createTime = demoAdvisor.getCreateTime();
        String currSql = null == createTime ? INSERT_ADVISOR_SQL : INSERT_ADVISOR_SQL_1;

        try (final Connection conn = connectionService.getConn();
             final PreparedStatement preparedStatement = conn.prepareStatement(currSql))
        {
            preparedStatement.setString(1, demoAdvisor.getName());
            preparedStatement.setString(2, demoAdvisor.getRate());
            if (null != createTime) {
                preparedStatement.setDate(3, new java.sql.Date(new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").parse(createTime).getTime()));
            }
            preparedStatement.execute();
        } catch (Exception e) {
//            log.error("SQL执行异常", e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public DemoAdvisor selectLatestAdvisor() {

        DemoAdvisor advisor = new DemoAdvisor();
        try (final Connection conn = connectionService.getConn();
             final PreparedStatement preparedStatement = conn.prepareStatement(SELECT_ADVISOR_SQL);
             final ResultSet resultSet = preparedStatement.executeQuery())
        {
            if (null != resultSet && resultSet.next()) {
                advisor.setId(resultSet.getInt("id"));
                advisor.setName(resultSet.getString("name"));
                advisor.setRate(resultSet.getString("rate"));
                advisor.setCreateTime(resultSet.getString("create_time"));
                advisor.setUpdateTime(resultSet.getString("update_time"));
            }
        } catch (Exception e) {
            log.error("SQL执行异常", e);
            throw new RuntimeException(e);
        }
        return advisor;
    }
}

package com.netease.cloud.nsf.db.config;


import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import javax.sql.DataSource;

@Configuration
@MapperScan(basePackages = "com.netease.cloud.nsf.db.configservice", sqlSessionFactoryRef = "datasource1SqlSessionFactory")
public class Db1Config {

    @Primary
    @Bean(name = "dataSource1Properties")
    @ConfigurationProperties(prefix = "spring.datasource1")
    public DataSourceProperties dataSource1Properties() {
        return new DataSourceProperties();
    }

    // DataSource的实例创建
    @Primary
    @Bean(name = "datasource1")
    public DataSource dataSource1(@Qualifier("dataSource1Properties") DataSourceProperties dataSource1Properties) {
        return dataSource1Properties.initializeDataSourceBuilder().build();
    }

    // ibatis 对应的SqlSession工厂类
    @Primary
    @Bean("datasource1SqlSessionFactory")
    public SqlSessionFactory datasource1SqlSessionFactory(@Qualifier("datasource1")DataSource dataSource1) throws Exception {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(dataSource1);
        bean.setMapperLocations(
                // 设置mybatis的xml所在位置
                new PathMatchingResourcePatternResolver().getResources("classpath:com/netease/cloud/nsf/db/configservice/*.xml"));
        return bean.getObject();
    }

    @Primary
    @Bean("datasource1SqlSessionTemplate")
    public SqlSessionTemplate datasource1SqlSessionTemplate(@Qualifier("datasource1SqlSessionFactory")SqlSessionFactory datasource1SqlSessionFactory) {
        return new SqlSessionTemplate(datasource1SqlSessionFactory);
    }
}

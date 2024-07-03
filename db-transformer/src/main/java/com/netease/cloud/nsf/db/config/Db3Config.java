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
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import javax.sql.DataSource;

@Configuration
@MapperScan(basePackages = "com.netease.cloud.nsf.db.configportal", sqlSessionFactoryRef = "datasource3SqlSessionFactory")
public class Db3Config {

    @Bean(name = "dataSource3Properties")
    @ConfigurationProperties(prefix = "spring.datasource3")
    public DataSourceProperties dataSource3Properties() {
        return new DataSourceProperties();
    }

    // DataSource的实例创建
    @Bean(name = "datasource3")
    public DataSource dataSource3(@Qualifier("dataSource3Properties") DataSourceProperties dataSource3Properties) {
        return dataSource3Properties.initializeDataSourceBuilder().build();
    }

    // ibatis 对应的SqlSession工厂类
    @Bean("datasource3SqlSessionFactory")
    public SqlSessionFactory datasource3SqlSessionFactory(@Qualifier("datasource3") DataSource dataSource3) throws Exception {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(dataSource3);
        bean.setMapperLocations(
                // 设置mybatis的xml所在位置
                new PathMatchingResourcePatternResolver().getResources("classpath:com/netease/cloud/nsf/db/configportal/*.xml"));
        return bean.getObject();
    }

    @Bean("datasource3SqlSessionTemplate")
    public SqlSessionTemplate datasource3SqlSessionTemplate(@Qualifier("datasource3SqlSessionFactory") SqlSessionFactory datasource3SqlSessionFactory) {
        return new SqlSessionTemplate(datasource3SqlSessionFactory);
    }
}

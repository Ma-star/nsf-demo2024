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
@MapperScan(basePackages = "com.netease.cloud.nsf.db.platform", sqlSessionFactoryRef = "datasource2SqlSessionFactory")
public class Db2Config {

    @Bean(name = "dataSource2Properties")
    @ConfigurationProperties(prefix = "spring.datasource2")
    public DataSourceProperties dataSource2Properties() {
        return new DataSourceProperties();
    }

    // DataSource的实例创建
    @Bean(name = "datasource2")
    public DataSource dataSource2(@Qualifier("dataSource2Properties") DataSourceProperties dataSource2Properties) {
        return dataSource2Properties.initializeDataSourceBuilder().build();
    }

    // ibatis 对应的SqlSession工厂类
    @Bean("datasource2SqlSessionFactory")
    public SqlSessionFactory datasource2SqlSessionFactory(@Qualifier("datasource2")DataSource dataSource2) throws Exception {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(dataSource2);
        bean.setMapperLocations(
                // 设置mybatis的xml所在位置
                new PathMatchingResourcePatternResolver().getResources("classpath:com/netease/cloud/nsf/db/platform/*.xml"));
        return bean.getObject();
    }

    @Bean("datasource2SqlSessionTemplate")
    public SqlSessionTemplate datasource2SqlSessionTemplate(@Qualifier("datasource2SqlSessionFactory")SqlSessionFactory datasource2SqlSessionFactory) {
        return new SqlSessionTemplate(datasource2SqlSessionFactory);
    }
}

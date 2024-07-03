package com.netease.cloud.nsf.demo.stock.advisor.web.database;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author: SUN_Huang (huangyang06@corp.netease.com)
 * @createTime: 2022/11/8 10:41
 */
@ConfigurationProperties(prefix = "nsf.database")
@Configuration
public class NsfDatabaseConfiguration {

    /**
     * 数据库地址
     */
    private String url;

    /**
     * 用户名
     */
    private String username;

    /**
     * 密码
     */
    private String password;

    public String getUrl(){
        return url;
    }

    public void setUrl(String url){
        this.url = url;
    }

    public String getUsername(){
        return username;
    }

    public void setUsername(String username){
        this.username = username;
    }

    public String getPassword(){
        return password;
    }

    public void setPassword(String password){
        this.password = password;
    }
}

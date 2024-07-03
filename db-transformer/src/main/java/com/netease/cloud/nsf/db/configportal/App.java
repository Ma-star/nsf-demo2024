package com.netease.cloud.nsf.db.configportal;

import com.tg.dao.annotation.Table;

@Table(name = "App")
public class App {
    private Long Id;
    public String AppId;
    public String Name;

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public String getAppId() {
        return AppId;
    }

    public void setAppId(String appId) {
        AppId = appId;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }
}

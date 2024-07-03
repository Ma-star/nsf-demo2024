package com.netease.cloud.nsf.db.configservice;

import com.tg.dao.annotation.Table;

@Table(name = "InstanceConfig")
public class InstanceConfig {
    private Long Id;
    public String ConfigAppId;

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public String getConfigAppId() {
        return ConfigAppId;
    }

    public void setConfigAppId(String configAppId) {
        ConfigAppId = configAppId;
    }
}

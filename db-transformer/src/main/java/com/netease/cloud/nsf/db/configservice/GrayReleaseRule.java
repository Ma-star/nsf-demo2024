package com.netease.cloud.nsf.db.configservice;

import com.tg.dao.annotation.Table;

@Table(name = "GrayReleaseRule")
public class GrayReleaseRule {
    private Long Id;
    public String AppId;

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

}

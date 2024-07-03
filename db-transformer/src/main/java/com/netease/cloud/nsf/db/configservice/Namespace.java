package com.netease.cloud.nsf.db.configservice;

import com.tg.dao.annotation.Table;

@Table(name = "Namespace")
public class Namespace {
    private Long Id;
    public String AppId;
    public String NamespaceName;

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

    public String getNamespaceName() {
        return NamespaceName;
    }

    public void setNamespaceName(String namespaceName) {
        NamespaceName = namespaceName;
    }
}

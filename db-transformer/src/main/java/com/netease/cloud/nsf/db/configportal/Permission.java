package com.netease.cloud.nsf.db.configportal;

import com.tg.dao.annotation.Table;

@Table(name = "Permission")
public class Permission {
    private Long Id;
    public String TargetId;

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public String getTargetId() {
        return TargetId;
    }

    public void setTargetId(String targetId) {
        TargetId = targetId;
    }
}

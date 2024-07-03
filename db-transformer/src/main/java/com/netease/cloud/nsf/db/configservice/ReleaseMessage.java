package com.netease.cloud.nsf.db.configservice;

import com.tg.dao.annotation.Table;

@Table(name = "ReleaseMessage")
public class ReleaseMessage {
    private Long Id;
    public String Message;

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

}

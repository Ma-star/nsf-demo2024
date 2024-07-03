package com.netease.cloud.nsf.db.configportal;

import com.tg.dao.annotation.Table;

@Table(name = "Role")
public class Role {
    private Long Id;
    public String RoleName;

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public String getRoleName() {
        return RoleName;
    }

    public void setRoleName(String roleName) {
        RoleName = roleName;
    }
}

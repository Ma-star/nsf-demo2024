package com.netease.cloud.nsf.db.platform;

import com.tg.dao.annotation.Table;

@Table(name = "permission_scope_info")
public class PermissionScope {
    public long id;
    public String permission_scope_name;
    public String permission_scope_en_name;
    public String permission_scope_type;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getPermission_scope_name() {
        return permission_scope_name;
    }

    public void setPermission_scope_name(String permission_scope_name) {
        this.permission_scope_name = permission_scope_name;
    }

    public String getPermission_scope_en_name() {
        return permission_scope_en_name;
    }

    public void setPermission_scope_en_name(String permission_scope_en_name) {
        this.permission_scope_en_name = permission_scope_en_name;
    }

    public String getPermission_scope_type() {
        return permission_scope_type;
    }

    public void setPermission_scope_type(String permission_scope_type) {
        this.permission_scope_type = permission_scope_type;
    }
}

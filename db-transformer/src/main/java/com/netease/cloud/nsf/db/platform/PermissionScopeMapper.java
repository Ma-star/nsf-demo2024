package com.netease.cloud.nsf.db.platform;

import com.tg.dao.annotation.*;
import com.tg.dao.constant.SqlMode;
import org.apache.ibatis.annotations.Mapper;

@Mapper
@DaoGen(model = PermissionScope.class)
public interface PermissionScopeMapper {

    @Select(columns = "id,permission_scope_name,permission_scope_type,permission_scope_en_name", sqlMode = SqlMode.COMMON)
    PermissionScope select(@Condition String id);

    @Update(columns = "AppId,Name")
    @ModelConditions(@ModelCondition(field = "id"))
    int update(PermissionScope permissionScope);
}

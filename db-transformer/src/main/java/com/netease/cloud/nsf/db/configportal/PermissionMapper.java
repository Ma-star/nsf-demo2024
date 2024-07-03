package com.netease.cloud.nsf.db.configportal;

import com.tg.dao.annotation.*;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
@DaoGen(model = Permission.class)
public interface PermissionMapper {

    @Select(columns = "Id,TargetId")
    List<Permission> list();

    @Update(columns = "TargetId")
    @ModelConditions(@ModelCondition(field = "Id"))
    int update(Permission permission);

}

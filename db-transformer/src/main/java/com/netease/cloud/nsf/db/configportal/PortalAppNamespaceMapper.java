package com.netease.cloud.nsf.db.configportal;

import com.tg.dao.annotation.*;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
@DaoGen(model = AppNamespace.class)
public interface PortalAppNamespaceMapper {

    @Select(columns = "Id,AppId,Name")
    List<AppNamespace> list();

    @Update(columns = "AppId,Name")
    @ModelConditions(@ModelCondition(field = "Id"))
    int update(AppNamespace appNamespace);

}
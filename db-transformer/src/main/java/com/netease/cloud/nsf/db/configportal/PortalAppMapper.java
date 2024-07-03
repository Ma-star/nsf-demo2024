package com.netease.cloud.nsf.db.configportal;

import com.tg.dao.annotation.*;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
@DaoGen(model = App.class)
public interface PortalAppMapper {

    @Select(columns = "Id,AppId,Name")
    List<App> list();

    @Update(columns = "AppId,Name")
    @ModelConditions(@ModelCondition(field = "Id"))
    int update(App app);

}
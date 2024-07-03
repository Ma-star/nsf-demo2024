package com.netease.cloud.nsf.db.configservice;

import com.tg.dao.annotation.*;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
//@DaoGen(model = Release.class)
public interface ReleaseMapper {

    @Select(columns = "Id,AppId,NamespaceName")
    List<Release> list();

    @Update(columns = "AppId,NamespaceName")
    @ModelConditions(@ModelCondition(field = "Id"))
    int update(Release release);

}

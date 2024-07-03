package com.netease.cloud.nsf.db.configservice;

import com.tg.dao.annotation.*;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
@DaoGen(model = Namespace.class)
public interface NamespaceMapper {

    @Select(columns = "Id,AppId,NamespaceName")
    List<Namespace> list();

    @Update(columns = "AppId,NamespaceName")
    @ModelConditions(@ModelCondition(field = "Id"))
    int update(Namespace namespace);

}

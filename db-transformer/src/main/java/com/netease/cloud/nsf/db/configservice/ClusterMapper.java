package com.netease.cloud.nsf.db.configservice;

import com.tg.dao.annotation.*;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
@DaoGen(model = Cluster.class)
public interface ClusterMapper {

    @Select(columns = "Id,AppId")
    List<Cluster> list();

    @Update(columns = "AppId")
    @ModelConditions(@ModelCondition(field = "Id"))
    int update(Cluster cluster);

}

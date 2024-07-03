package com.netease.cloud.nsf.db.configservice;

import com.tg.dao.annotation.*;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
@DaoGen(model = Instance.class)
public interface InstanceMapper {

    @Select(columns = "Id,AppId")
    List<Instance> list();

    @Update(columns = "AppId")
    @ModelConditions(@ModelCondition(field = "Id"))
    int update(Instance instance);

}

package com.netease.cloud.nsf.db.configservice;

import com.tg.dao.annotation.*;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
@DaoGen(model = InstanceConfig.class)
public interface InstanceConfigMapper {

    @Select(columns = "Id,ConfigAppId")
    List<InstanceConfig> list();

    @Update(columns = "ConfigAppId")
    @ModelConditions(@ModelCondition(field = "Id"))
    int update(InstanceConfig instanceConfig);

}

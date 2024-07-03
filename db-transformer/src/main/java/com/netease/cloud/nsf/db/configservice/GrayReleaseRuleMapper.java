package com.netease.cloud.nsf.db.configservice;

import com.tg.dao.annotation.*;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
@DaoGen(model = GrayReleaseRule.class)
public interface GrayReleaseRuleMapper {

    @Select(columns = "Id,AppId")
    List<GrayReleaseRule> list();

    @Update(columns = "AppId")
    @ModelConditions(@ModelCondition(field = "Id"))
    int update(GrayReleaseRule grayReleaseRule);

}

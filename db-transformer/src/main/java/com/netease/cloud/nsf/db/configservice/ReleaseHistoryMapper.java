package com.netease.cloud.nsf.db.configservice;

import com.tg.dao.annotation.*;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
@DaoGen(model = ReleaseHistory.class)
public interface ReleaseHistoryMapper {

    @Select(columns = "Id,AppId,NamespaceName")
    List<ReleaseHistory> list();

    @Update(columns = "AppId,NamespaceName")
    @ModelConditions(@ModelCondition(field = "Id"))
    int update(ReleaseHistory releaseHistory);

}

package com.netease.cloud.nsf.db.configservice;

import com.tg.dao.annotation.*;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
@DaoGen(model = Commit.class)
public interface CommitMapper {

    @Select(columns = "Id,AppId")
    List<Commit> list();

    @Update(columns = "AppId")
    @ModelConditions(@ModelCondition(field = "Id"))
    int update(Commit commit);

}

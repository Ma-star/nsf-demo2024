package com.netease.cloud.nsf.db.configservice;

import com.tg.dao.annotation.*;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
@DaoGen(model = ReleaseMessage.class)
public interface ReleaseMessageMapper {

    @Select(columns = "Id,Message")
    List<ReleaseMessage> list();

    @Update(columns = "Message")
    @ModelConditions(@ModelCondition(field = "Id"))
    int update(ReleaseMessage releaseMessage);

}

package com.netease.cloud.nsf.db.configportal;

import com.tg.dao.annotation.*;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
@DaoGen(model = Role.class)
public interface RoleMapper {

    @Select(columns = "Id,RoleName")
    List<Role> list();

    @Update(columns = "RoleName")
    @ModelConditions(@ModelCondition(field = "Id"))
    int update(Role role);

}

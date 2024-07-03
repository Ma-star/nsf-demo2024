package com.netease.cloud.nsf.demo.stock.advisor.web.entity;

import java.sql.Date;

/**
 * @author: SUN_Huang (huangyang06@corp.netease.com)
 * @createTime: 2022/11/8 14:31
 */
public class DemoAdvisor {

    /**
     * 主键
     */
    private int id;
    /**
     * 名称
     */
    private String name;
    /**
     * 速率
     */
    private String rate;
    /**
     * 时间
     */
    private String createTime;
    /**
     * 时间
     */
    private String updateTime;

    public int getId(){
        return id;
    }

    public void setId(int id){
        this.id = id;
    }

    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
    }

    public String getRate(){
        return rate;
    }

    public void setRate(String rate){
        this.rate = rate;
    }

    public String getCreateTime(){
        return createTime;
    }

    public void setCreateTime(String createTime){
        this.createTime = createTime;
    }

    public String getUpdateTime(){
        return updateTime;
    }

    public void setUpdateTime(String updateTime){
        this.updateTime = updateTime;
    }

    @Override
    public String toString(){
        return "DemoAdvisor{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", rate='" + rate + '\'' +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                '}';
    }
}

package com.netease.cloud.nsf.demo.stock.advisor.web.service;

import com.netease.cloud.nsf.demo.stock.advisor.web.entity.DemoAdvisor;

/**
 * @author: SUN_Huang (huangyang06@corp.netease.com)
 * @createTime: 2022/11/10 17:02
 */
public interface IDatabaseService {

    /**
     * 新增
     *
     * @param demoAdvisor [demoAdvisor]
     */
    void insertAdvisor(DemoAdvisor demoAdvisor);

    /**
     * 查询最新的数据
     *
     * @return [DemoAdvisor]
     */
    DemoAdvisor selectLatestAdvisor();
}

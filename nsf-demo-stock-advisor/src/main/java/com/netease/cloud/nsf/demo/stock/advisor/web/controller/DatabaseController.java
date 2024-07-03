package com.netease.cloud.nsf.demo.stock.advisor.web.controller;

import com.netease.cloud.nsf.demo.stock.advisor.web.entity.DemoAdvisor;
import com.netease.cloud.nsf.demo.stock.advisor.web.service.IDatabaseService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: SUN_Huang (huangyang06@corp.netease.com)
 * @createTime: 2022/11/8 11:32
 */
@RestController
public class DatabaseController {

    private final static Logger log = LoggerFactory.getLogger(DatabaseController.class);

    @Autowired
    private IDatabaseService iDatabaseService;

    /**
     * Database: nsf_demo
     * Table: nsf_advisor
     * columns:
     *  - id
     *  - name
     *  - rate
     *  - time
     */
    @PostMapping("/db_write")
    public String writeToDb(@RequestBody DemoAdvisor demoAdvisor) {
        log.info("write_db, advisor: {}", demoAdvisor);
        iDatabaseService.insertAdvisor(demoAdvisor);
        log.info("write_db, advisor: SUCCESS");
        return demoAdvisor.getName() + " INSERT SUCCESS";
    }

    @GetMapping("/db_read")
    public DemoAdvisor readToDb() {
        log.info("read_db");
        return iDatabaseService.selectLatestAdvisor();
    }
}

package com.netease.cloud.nsf.demo.zk.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@RestController
public class MainController {

    private static final Logger logger = LoggerFactory.getLogger(MainController.class);

    @Autowired
    private ZkService zkService;

    @GetMapping("/health")
    public String health() {
        return "ok";
    }


    @GetMapping("/status")
    public Map<String, Object> status() {
        Map<String, Object> res = new HashMap<>();
        res.put("dubbo interface 数", zkService.ifaceNum);
        res.put("zk address", zkService.ifaceNum);
        res.put("zk node 定期变化开关", zkService.changeNode);
        res.put("zk node 变化间隔", zkService.changeInterval);
        return res;
    }

    @GetMapping("/doTask")
    public String doTask(HttpServletRequest request) {
        if (!zkService.changeNode) {
            zkService.changeNode = true;
        }
        zkService.startTask();
        return "ok";
    }

    @GetMapping("/stopTask")
    public String stopTask(HttpServletRequest request) {
        zkService.changeNode = false;
        return "ok";
    }

}

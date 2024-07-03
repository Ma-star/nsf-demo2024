package com.netease.cloud.nsf.demo.stock.advisor.web.controller;

import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * @author: SUN_Huang (huangyang06@corp.netease.com)
 * @createTime: 2021/4/20 9:37 上午
 */
@RestController
@RequestMapping("/de")
public class DegradeDemo {

    private final static Logger LOGGER = LoggerFactory.getLogger(DegradeDemo.class);

    private final static String SUCCESS = "success";

    public static void main(String[] args){
        int x = 0;
        for (int i = 0; i < 100; i++) {
            if (EX_RATIO.nextInt(100) < 30)
                x++;
        }
        System.out.println(x);
    }

    /* 1.异常比例 */

    private final static Random EX_RATIO = new Random();
    
    
    /**
     * /de/bizRatio?ratio=100&code=4xx 全部返回异常
     * /de/bizRatio?ratio=20&code=4xx
     *
     * 200但业务错误 (statusCode==200 && SYS_TX_STATUS!=00)
     * 1.业务错误比例数值: ratio
     */
    @RequestMapping("/bizRatio")
    public ResponseEntity <String> bizRatio(@RequestParam(value = "ratio") int ratio) {
        LOGGER.info("bizRatio invoked");
        checkRatio(ratio);
        int c = 200;
        String template = "<TX><TX_HEADER><SYS_TX_STATUS>%s</SYS_TX_STATUS></TX_HEADER></TX>";
        String msg = null;
        if (EX_RATIO.nextInt(100) < ratio) {
            msg = String.format(template, "99");
        }else {
            msg = String.format(template, "00");
        }
        return ResponseEntity
                .status(c)
                .contentType(MediaType.TEXT_PLAIN)
                .body(msg);
    }

    /**
     * /de/ratio?ratio=100&code=4xx 全部返回异常
     * /de/ratio?ratio=20&code=4xx
     *
     * 4xx/5xx 异常比例 (4xx -> 400, 5xx -> 500)
     * 1.异常类型: runtime
     * 2.比例数值: ratio
     */
    @RequestMapping("/ratio")
    public ResponseEntity <String> ratio(@RequestParam(value = "ratio") int ratio,
                                                  @RequestParam(value = "code") String code) {
        LOGGER.info("ratio invoked");
        checkRatio(ratio);
        int c = 200;
        String msg = SUCCESS;
        if (!"5xx".equals(code) && !"4xx".equals(code)) {
            msg = "code should be in [4xx, 5xx]";
        } else {
            if (EX_RATIO.nextInt(100) < ratio) {
                c = "5xx".equals(code) ? 500 : 400;
                msg += " from " + code;
            }
        }
        return ResponseEntity
                .status(c)
                .contentType(MediaType.APPLICATION_JSON)
                .body(JSONObject.toJSONString(getMap(msg, String.valueOf(c), "exceptionRatio", "/de/ratio")));
    }

    /**
     * 百分百睡眠指定超时时间 /de/sleep?ratio=100&time=5
     *
     * 连接超时
     */
    @RequestMapping("/sleep")
    public String sleep(@RequestParam(value = "ratio") int ratio,
                        @RequestParam(value = "time", required = false) Integer seconds) {
        LOGGER.info("sleep invoked");
        checkRatio(ratio);
        if (null != seconds && seconds > 0) {
            try {
                if (EX_RATIO.nextInt(100) < ratio) {
                    TimeUnit.SECONDS.sleep(seconds);
                }
            } catch (InterruptedException ignore) {
            }
        }
        return SUCCESS;
    }

    /**
     * 200返回指定错误码
     *
     * /de/args?ratio=100&args=xxx
     */
    @RequestMapping("/args")
    public String args(@RequestParam(value = "ratio") int ratio,
                          @RequestParam(value = "args") String args) {
        LOGGER.info("args invoked");
        checkRatio(ratio);
        return EX_RATIO.nextInt(100) < ratio ? SUCCESS + ": " + args : SUCCESS;
    }

    private Map <String, Object> getMap(String message, String code, String method, String path) {
        Map <String, Object> as = new HashMap <>();
        as.put("message", message);
        as.put("code", code);
        as.put("method", method);
        as.put("path", path);
        return as;
    }

    /**
     * ratio [0, 100]
     * 0: 表示不生效 走其他逻辑 (就相当于一个成功接口)
     * 100: 表示全走异常逻辑
     */
    private void checkRatio(int ratio) {
        if (ratio < 0 || ratio > 100) {
            throw new RuntimeException("ratio must be in [0, 100]");
        }
    }
}

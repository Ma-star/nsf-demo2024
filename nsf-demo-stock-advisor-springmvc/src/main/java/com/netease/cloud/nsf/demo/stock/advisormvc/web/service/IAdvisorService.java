package com.netease.cloud.nsf.demo.stock.advisormvc.web.service;



import com.netease.cloud.nsf.demo.stock.advisormvc.web.entity.Stock;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface IAdvisorService {
    public List<Stock> getHotStocks() throws Exception;

    public List<String> batchHi();

    public String deepInvoke(int times);

    public String echobyecho();

    public String echobyechoAsync();

    public String divide(HttpServletRequest request);

    public String sendProvider(String content, int delay, String providerName);

}

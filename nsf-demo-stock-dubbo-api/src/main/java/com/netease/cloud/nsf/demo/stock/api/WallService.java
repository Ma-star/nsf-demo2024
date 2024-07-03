package com.netease.cloud.nsf.demo.stock.api;

import com.netease.cloud.nsf.demo.stock.api.entity.Stock;

import java.util.List;

public interface WallService {

    List<Stock> getStockList(int delay);

    List<Stock> getHotAdvice() throws Exception;

    Stock getStockById(String stockId);

    String echoAdvisor();

    String echoProvider();

    String content(String content, int delay);

    String getConfig(String namespace);

    Object getDebugConfig(String preFix, String type);

    String echoStrAndInt(String key, int num);

    String echoStrAndInt1(String key, int num);

    String echoAdvisorB();

    String echoProviderB();

    String echoStrAndIntB(String key, int num);

    String echoStrAndInt1B(String key, int num);

    String echobyecho(Integer time);

    String getContextOnFixedSize(int size);

}

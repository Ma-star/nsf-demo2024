package com.netease.cloud.nsf.demo.zk.web;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.RetryForever;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.net.URLEncoder;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Component
public class ZkService {
    private static final Logger logger = LoggerFactory.getLogger(ZkService.class);
    private final int sessionTimeout = 30000;
    private final int connTimeout = 5000;

    @Value("${nsf.zk:127.0.0.1:2181}")
    public String zkUrl;

    @Value("${zk.ifaceNum:10}")
    public int ifaceNum;
    @Value("${zk.instanceNum:5}")
    public int instanceNum;

    public volatile boolean changeNode = false;

    @Value("${changeIfaceNum:5}")
    public int changeIfaceNum;
    @Value("${changeInterval:10}")
    public int changeInterval;
    @Value("${startupInterval:10}")
    public int startupInterval;

    private CuratorFramework client;

    private Thread shutdownHook = new Thread(new Runnable() {
        @Override
        public void run() {
            System.err.println("*** clean zk node since JVM is shutting down");
            clean();
            System.err.println("*** clean job down");
        }
    });

    @PostConstruct
    public void init() {
        RetryPolicy retryPolicy = new RetryForever(Integer.MAX_VALUE);
        client = CuratorFrameworkFactory.newClient(zkUrl, sessionTimeout, connTimeout, retryPolicy);
        client.start();
        logger.info("connect zookeeper {} successful", zkUrl);
        pre();
        Runtime.getRuntime().addShutdownHook(shutdownHook);
    }

    String root = "/dubbo/com.netease.cloud.nsf.demo.stock.api.EchoService%d";
    String provider = "dubbo://10.219.192.6%d:22822/com.netease.cloud.nsf.demo.stock.api.EchoService%d" +
            "?anyhost=true&application=nsf-demo-stock-dubbo-echo%d&application.version=0.0.8&dubbo=2.6.2&generic=false&" +
            "version=v1&interface=com.netease.cloud.nsf.demo.stock.api.EchoService%d&" +
            "methods=echobyecho,echo,echoForMap,echoStrAndInt1,echoForReturnVoid&pid=31116&side=provider&timestamp=%d";

    String consumer = "consumer://10.219.192.6%d/com.netease.cloud.nsf.demo.stock.api.EchoService%d?" +
            "application=nsf-demo-stock-dubbo-wall%d&application.version=0.0.1&category=consumers&check=false&dubbo=2.6.2" +
            "&version=v1&interface=com.netease.cloud.nsf.demo.stock.api.EchoService%d&methods=echobyecho,echoForMap,echo,content,echoStrAndInt&pid=37869&side=consumer&timestamp=%d";

    public void pre() {
        for (int i = 1; i < ifaceNum + 1; i++) {
            try {
                String path = String.format(root, i);
                try {
                    client.create().forPath(path);
                } catch (KeeperException.NodeExistsException e) {
                }
                String providerPath = path + "/providers";
                try {
                    client.create().forPath(providerPath);
                } catch (KeeperException.NodeExistsException e) {
                }

                String consumerPath = path + "/consumers";
                try {
                    client.create().forPath(consumerPath);
                } catch (KeeperException.NodeExistsException e) {
                }

                for (int j = 0; j < instanceNum; j++) {
                    String instance = providerPath + "/" + URLEncoder.encode(String.format(provider, j, i, i, i, System.currentTimeMillis()));
                    try {
                        client.create().withMode(CreateMode.EPHEMERAL).forPath(instance);
                    } catch (KeeperException.NodeExistsException e) {
                    }
                }

                for (int j = 0; j < instanceNum; j++) {
                    String instance = consumerPath + "/" + URLEncoder.encode(String.format(consumer, j, i, i, i, System.currentTimeMillis()));
                    try {
                        client.create().withMode(CreateMode.EPHEMERAL).forPath(instance);
                    } catch (KeeperException.NodeExistsException e) {
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            try {
                TimeUnit.MILLISECONDS.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void startTask() {
        if (changeNode) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    timingTask();
                }
            }).start();
        }
    }


    private void timingTask() {
        int index = 1;
        while (changeNode) {

            try {
                int i = index;
                logger.info("delete change index:{}", index);
                for (; i < index + changeIfaceNum; i++) {
                    String path = String.format(root, i);
                    String providerPath = path + "/providers";
                    String consumerPath = path + "/consumers";
                    //先删除
                    List<String> list = client.getChildren().forPath(providerPath);
                    for (String node : list) {
                        try {
                            client.delete().forPath(providerPath + "/" + node);
                        } catch (KeeperException.NodeExistsException e) {
                        }
                    }
                    list = client.getChildren().forPath(consumerPath);
                    for (String node : list) {
                        try {
                            client.delete().forPath(consumerPath + "/" + node);
                        } catch (KeeperException.NodeExistsException e) {
                        }
                    }
                    if (i == ifaceNum - 1) {
                        break;
                    }
                }
                //间隔5秒
                try {
                    TimeUnit.SECONDS.sleep(startupInterval);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                logger.info("add change index:{}", index);
                //新增
                for (i = index; i < index + changeIfaceNum; i++) {
                    String path = String.format(root, i);
                    String providerPath = path + "/providers";
                    String consumerPath = path + "/consumers";
                    for (int j = 0; j < instanceNum; j++) {
                        String instance = providerPath + "/" + URLEncoder.encode(String.format(provider, j, i, i, i, System.currentTimeMillis()));
                        try {
                            client.create().withMode(CreateMode.EPHEMERAL).forPath(instance);
                        } catch (KeeperException.NodeExistsException e) {
                        }
                    }

                    for (int j = 0; j < instanceNum; j++) {
                        String instance = consumerPath + "/" + URLEncoder.encode(String.format(consumer, j, i, i, i, System.currentTimeMillis()));
                        try {
                            client.create().withMode(CreateMode.EPHEMERAL).forPath(instance);
                        } catch (KeeperException.NodeExistsException e) {
                        }
                    }
                    if (i == ifaceNum - 1) {
                        break;
                    }
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
            index = index + changeIfaceNum;
            if (index > ifaceNum) {
                index = 1;
            }
            try {
                TimeUnit.SECONDS.sleep(changeInterval);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void clean() {
        for (int i = 1; i < ifaceNum + 1; i++) {
            try {
                String path = String.format(root, i);
                String providerPath = path + "/providers";
                String consumerPath = path + "/consumers";

                List<String> list = client.getChildren().forPath(providerPath);
                for (String node : list) {
                    try {
                        client.delete().forPath(providerPath + "/" + node);
                    } catch (KeeperException.NodeExistsException e) {
                    }
                }

                list = client.getChildren().forPath(consumerPath);
                for (String node : list) {
                    try {
                        client.delete().forPath(consumerPath + "/" + node);
                    } catch (KeeperException.NodeExistsException e) {
                    }
                }

                try {
                    client.delete().forPath(consumerPath);
                } catch (KeeperException.NodeExistsException e) {
                }

                try {
                    client.delete().forPath(providerPath);
                } catch (KeeperException.NodeExistsException e) {
                }

                try {
                    client.delete().forPath(path);
                } catch (KeeperException.NodeExistsException e) {
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            try {
                TimeUnit.MILLISECONDS.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

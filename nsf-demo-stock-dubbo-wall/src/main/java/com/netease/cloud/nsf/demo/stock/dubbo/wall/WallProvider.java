package com.netease.cloud.nsf.demo.stock.dubbo.wall;

import java.util.concurrent.CountDownLatch;
import java.net.*;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.netease.cloud.nsf.demo.stock.dubbo.wall.config.WallConfig;

public class WallProvider {

	public static CountDownLatch SHUTDOWN_LATCH = new CountDownLatch(1);
//	public static EchoService ech;
	
	public static void main(String[] args) throws SocketException, InterruptedException {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(WallConfig.class);
		context.start();
//        ech = ((EchoService) context.getBean("echoService"));
//        DatagramSocket socketServer = new DatagramSocket(19991);
//        while(true){
//            try {
//                byte[] buf = new byte[1024];
//                DatagramPacket dp_receive = new DatagramPacket(buf, 1024);
//                socketServer.receive(dp_receive);
//                String key = new String(dp_receive.getData());
//                System.out.println("=============="+key);
//                key = key.substring(0,key.lastIndexOf('\n'));
//                ech.echo();
//                ech.echoStrAndInt(key, 49);
//                ech.echoStrAndInt1(key + 1, 51);
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        }

		SHUTDOWN_LATCH.await();
	}

}

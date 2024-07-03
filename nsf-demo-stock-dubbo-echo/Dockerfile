FROM  hub.c.163.com/qingzhou/multi-arch/tomcat-8.5-jdk8-openjdk-skiff:v1.0
ADD target/nsf-demo-stock-dubbo-echo*-dependencies.jar /dubbo/nsf-demo.jar
ADD start.sh /dubbo/script/start.sh

CMD ["sh","/dubbo/script/start.sh"]
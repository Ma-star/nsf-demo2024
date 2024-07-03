FROM  hub.c.163.com/qingzhou/multi-arch/tomcat-8.5-jdk8-openjdk-skiff:v1.0

ENV TZ=Asia/Shanghai LANG=C.UTF-8 LANGUAGE=C.UTF-8 LC_ALL=C.UTF-8

RUN ln -snf /usr/share/zoneinfo/$TZ /etc/localtime && echo $TZ > /etc/timezone

ADD target/nsf-demo-stock-dubbo-wall*-dependencies.jar /dubbo-wall/nsf-demo-stock-dubbo-wall.jar
ADD start.sh /dubbo-wall/script/start.sh

CMD ["sh","/dubbo-wall/script/start.sh"]


ARG BASE_IMAGE=haarbor.cloud.netease.com/qztest/cicd-mvn3.5-jdk8:v2.1.1

FROM ${BASE_IMAGE}

ENV TZ=Asia/Shanghai LANG=C.UTF-8 LANGUAGE=C.UTF-8 LC_ALL=C.UTF-8

RUN ln -snf /usr/share/zoneinfo/$TZ /etc/localtime && echo $TZ > /etc/timezone

#add application jar
ADD ./nsf-demo-stock-viewer/target/*.jar /opt/nsf/

ENV JAVA_OPTS=''

CMD java $JAVA_OPTS -jar /opt/nsf/nsf-demo-stock-viewer-1.0-SNAPSHOT.jar
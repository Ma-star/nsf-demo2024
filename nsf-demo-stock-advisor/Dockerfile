FROM  hub.c.163.com/qingzhou/multi-arch/tomcat-8.5-jdk8-openjdk-skiff:v1.0
ADD target/nsf-demo-*.jar /app.jar

ADD start.sh /start.sh

CMD ["sh","/start.sh"]
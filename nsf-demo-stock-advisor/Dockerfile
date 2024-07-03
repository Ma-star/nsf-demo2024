FROM harbor.cloud.netease.com/qztest/cicd-mvn3.5-jdk8:v2.1.1 
ADD target/nsf-demo-*.jar /app.jar

ADD start.sh /start.sh

CMD ["sh","/start.sh"]

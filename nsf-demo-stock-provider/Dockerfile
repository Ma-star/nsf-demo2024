FROM  harbor.cloud.netease.com/qztest/cicd-mvn3.5-jdk8:v2.1.1 
ENV TZ=Asia/Shanghai LANG=C.UTF-8 LANGUAGE=C.UTF-8 LC_ALL=C.UTF-8
ENV ROTATE_LOG_SIZE=1000M
ENV ROTATE_LOG_INTERVAL=86400
ENV ROTATE_LOG_FILE_NAME=catalina
ENV NCE_APPNAME=nsf-server
RUN ln -snf /usr/share/zoneinfo/$TZ /etc/localtime && echo $TZ > /etc/timezone
WORKDIR /usr/local/tomcat
RUN rm -rf webapps/* \
    && mkdir webapps/ROOT
#    && sed -i '0,/3manager.org.apache.juli.FileHandler.prefix/s//3manager.org.apache.juli.FileHandler.maxDays = $LOGROTATE_LOGDAY\n&/' $CATALINA_HOME/conf/logging.properties \
#    && sed -i '/3manager.org.apache.juli.FileHandler.directory/c\3manager.org.apache.juli.FileHandler.directory = $LOGROTATE_LOGPATH' $CATALINA_HOME/conf/logging.properties
COPY build bin/
COPY target/*.war webapps/ROOT
RUN cd webapps/ROOT \
    && jar -xvf $(ls | grep '.war') \
    && rm $(ls | grep '.war') \
    && chmod +x $CATALINA_HOME/bin/docker-entrypoint.sh
#ENTRYPOINT ["bin/docker-entrypoint.sh"]
CMD ["catalina.sh", "run"]

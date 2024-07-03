#!/bin/bash

if [ "$NSF_AGENT_URL" ];then
mkdir -p /home/nsf-agent
export NSF_AGENT_PATH="/home/nsf-agent/nsf-agent.jar"
wget $NSF_AGENT_URL -O $NSF_AGENT_PATH
echo NSF_AGENT_PATH=$NSF_AGENT_PATH
fi

if [ "$APM_URL" ]; then
  export APM_PATH="/home/skywalking-napm.tar.gz"
  wget $AGENT_ZIP_URL -O $APM_PATH
  tar -zxvf $APM_PATH -C "/home/"
fi

SERVICE_NAME=nsf-demo
JAR_PATH=/dubbo/nsf-demo.jar
chmod 777 $JAR_PATH
java $NCE_JAVA_OPTS -jar $JAR_PATH
exit 0;

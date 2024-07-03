#!/bin/bash

if [ "$AGENT_ZIP_URL" ]; then
  rm -rf /home/nsf-agent
  mkdir -p /home/nsf-agent
  export AGENT_ZIP_PATH="/home/nsf-agent/agent.zip"
  wget $AGENT_ZIP_URL -O $AGENT_ZIP_PATH
  unzip -o $AGENT_ZIP_PATH -d "/home/nsf-agent"
  cp "/home/nsf-agent/agent/agent.jar" $NSF_AGENT_PATH
elif [ "$NSF_AGENT_URL" ]; then
  rm -rf /home/nsf-agent
  mkdir -p /home/nsf-agent
  export NSF_AGENT_PATH="/home/nsf-agent/nsf-agent.jar"
  wget $NSF_AGENT_URL -O $NSF_AGENT_PATH
fi

java $NCE_JAVA_OPTS -jar /app.jar

exit 0;
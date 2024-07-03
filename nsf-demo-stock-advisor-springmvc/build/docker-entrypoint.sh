#!/bin/sh

set -ex

echo "01 04 * * * $CATALINA_HOME/bin/skiff_clean_log.sh" > $CATALINA_HOME/bin/skiff_clean_log.cron
/etc/init.d/cron restart

env > $CATALINA_HOME/bin/envStorage
chmod +x $CATALINA_HOME/bin/skiff_clean_log.sh
sed -i "s#\$CATALINA_HOME#$CATALINA_HOME#g" $CATALINA_HOME/bin/skiff_clean_log.sh
crontab $CATALINA_HOME/bin/skiff_clean_log.cron


#local_access 日志开关
   if [ "$NCE_LOCAL_ACCESS_SWITCH" = "false" ]; then
    sed -i 's#<Valve className="org.apache.catalina.valves.AccessLogValve"#<!--<Valve className="org.apache.catalina.valves.AccessLogValve"#' $CATALINA_HOME/conf/server.xml
    sed -i 's#pattern="%h %l %u %t &quot;%r&quot; %s %b" />#pattern="%h %l %u %t &quot;%r&quot; %s %b" /> -->#' $CATALINA_HOME/conf/server.xml
   fi

if [ "$NCE_LOG_ROTATE_POLICY" = "capacity" ]; then
    $CATALINA_HOME/bin/catalina.sh run 2>&1 | /usr/bin/rotatelogs -l -f $CATALINA_HOME/logs/$ROTATE_LOG_FILE_NAME.log $ROTATE_LOG_SIZE
else
    if [ "$ROTATE_LOG_INTERVAL" -ge 86400 ]; then
        $CATALINA_HOME/bin/catalina.sh run 2>&1 | /usr/bin/rotatelogs -l -f $CATALINA_HOME/logs/${ROTATE_LOG_FILE_NAME}_%F.log $ROTATE_LOG_INTERVAL
    elif [[ "$ROTATE_LOG_INTERVAL" -lt 86400 && "$ROTATE_LOG_INTERVAL" -ge 3600 ]]; then
        $CATALINA_HOME/bin/catalina.sh run 2>&1 | /usr/bin/rotatelogs -l -f $CATALINA_HOME/logs/${ROTATE_LOG_FILE_NAME}_%F-%H.log $ROTATE_LOG_INTERVAL
    elif [[ "$ROTATE_LOG_INTERVAL" -lt 3600 && "$ROTATE_LOG_INTERVAL" -ge 60 ]]; then
        $CATALINA_HOME/bin/catalina.sh run 2>&1 | /usr/bin/rotatelogs -l -f $CATALINA_HOME/logs/${ROTATE_LOG_FILE_NAME}_%F-%R.log $ROTATE_LOG_INTERVAL
    else
        $CATALINA_HOME/bin/catalina.sh run 2>&1 | /usr/bin/rotatelogs -l -f $CATALINA_HOME/logs/${ROTATE_LOG_FILE_NAME}_%F-%T.log $ROTATE_LOG_INTERVAL
    fi
fi

exec "$@"
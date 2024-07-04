#!/usr/bin/env bash
java -javaagent:/opt/nsf/nsf-agent-fat.jar=nsf -Dnsf.grpc.timeout=15000 -Dstock_provider_url=http://nsf-provider -Dstock_viewer_url=http://nsf-viewer -jar /opt/nsf/nsf-demo-stock-advisor-1.0-SNAPSHOT.jar

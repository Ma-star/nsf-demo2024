#!/usr/bin/env bash
java -javaagent:/opt/nsf/nsf-agent-fat.jar=nsf -Dnsf.grpc.timeout=15000 -jar /opt/nsf/nsf-demo-stock-provider-1.0-SNAPSHOT.jar

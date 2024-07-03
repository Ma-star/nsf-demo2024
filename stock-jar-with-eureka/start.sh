#!/bin/bash
nohup java -jar ./scripts/nsf-eureka.jar &
#nohup java -Deureka.client.serviceUrl.defaultZone=http://localhost:8877/eureka/ -Dstock_provider_url=http://provider.test.eureka.sm -Dstock_advisor_url=http://nsf-demo-stock-advisor.foo.svc.cluster.ci -Deureka.instance.metadata-map.version=v1 -Dspring.application.name=viewer -jar ./scripts/viewer.jar  &
nohup java -Deureka.client.serviceUrl.defaultZone=http://localhost:8877/eureka/ -Dstock_provider_url=http://nsf-demo-stock-provider.foo.svc.cluster.ci -Dstock_advisor_url=http://nsf-demo-stock-advisor.foo.svc.cluster.ci -Deureka.instance.metadata-map.version=v1 -Dspring.application.name=viewer -jar ./scripts/viewer.jar  &
#nohup java -Deureka.client.serviceUrl.defaultZone=http://localhost:8877/eureka/ -Dstock_provider_url=http://provider.test.eureka.sm:9001 -Dstock_advisor_url=http://advisor.test.eureka.sm:9002 -Deureka.instance.metadata-map.version=v1 -Dspring.application.name=viewer -jar ./scripts/viewer.jar  &
nohup java -Deureka.client.serviceUrl.defaultZone=http://localhost:8877/eureka/ -Deureka.instance.metadata-map.version=v1 -Dspring.application.name=provider -Deureka.instance.metadata-map.version=v1 -jar ./scripts/provider.jar  &
nohup java -Derror=false -Deureka.client.serviceUrl.defaultZone=http://localhost:8877/eureka/ -Dstock_provider_url=http://provider.test.eureka.sm  -Deureka.instance.metadata-map.version=v1 -Dspring.application.name=advisor -jar ./scripts/advisor.jar  &


#!/bin/bash
[ "$REV_HASH" ] || REV_HASH=`git rev-parse HEAD`
NOW=`TZ=Asia/Shanghai date +%Y%m%d-%H%M%S`
docker buildx build -t harbor.cloud.netease.com/qztest/nsf-demo-stock-provider:develop-20190307-stable-${REV_HASH:0:8}-$NOW --platform=linux/arm64,linux/amd64 . --push

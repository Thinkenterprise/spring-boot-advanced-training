#!/bin/bash
docker run -d -p 9090:9090 --mount type=bind,source=<absolute-path>/prometheus.yml,target=/etc/prometheus/prometheus.yml prom/prometheus

#!/bin/bash
docker run -d -p 9090:9090 -p 8080:8080 -v ~/prometheus.yml:/etc/prometheus/prometheus.yml prom/prometheus

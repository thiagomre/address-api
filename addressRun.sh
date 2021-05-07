#!/bin/bash
chmod 755 addressRun.sh
./gradlew build
docker build -t spring/address-docker .
docker run -p 8080 spring/address-docker

#!/bin/bash

mkdir tmp

mvn clean package -f ../../../microservices/stalkerlog-auth/pom.xml
cp ../../../microservices/stalkerlog/target/stalkerlog-auth*.jar ./tmp/

docker build -t stalkerlog-auth/api .

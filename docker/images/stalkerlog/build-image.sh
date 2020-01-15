#!/bin/bash

mkdir tmp

mvn clean package -f ../../../microservices/stalkerlog/pom.xml
cp ../../../microservices/stalkerlog/target/stalkerlog*.jar ./tmp/

# docker build -t stalkerlog/api .

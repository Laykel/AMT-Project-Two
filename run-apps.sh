#!/bin/bash

rootDir=$(pwd)

cd docker/images/stalkerlog
source build-image.sh

cd $rootDir

cd docker/images/stalkerlog-auth
source build-image.sh

cd $rootDir

cd docker
docker-compose down -v
docker-compose up --build -d

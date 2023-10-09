#!/bin/bash

APPS=("ModifyPasteBinService" "GetPasteBinService")  # Replace with your application directory names.
DOCKER_REGISTRY="potrohit/pastebin-application"

for APP in "${APPS[@]}"
do
   cd $APP
   lower_app=$(echo "$APP" | tr '[:upper:]' '[:lower:]')
   mvn clean package spring-boot:repackage
   docker build -t $DOCKER_REGISTRY:$lower_app .
   docker push $DOCKER_REGISTRY:$lower_app
   cd ..
done
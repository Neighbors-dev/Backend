#!/bin/bash

set -e

ERR_MSG=''

trap 'echo "Error occured: $ERR_MSG. Exiting deploy script."; exit 1' ERR

if sudo docker ps --filter "name=neighbors-blue" --quiet | grep -E .; then
  RUN_TARGET="neighbors-green"
  STOP_TARGET="neighbors-blue"
  WAS_RUN_PORT=8081
  WAS_STOP_PORT=8080
else
  RUN_TARGET="neighbors-blue"
  STOP_TARGET="neighbors-green"
  WAS_RUN_PORT=8080
  WAS_STOP_PORT=8081
fi

echo "The $STOP_TARGET version is currently running on the server. Starting the $RUN_TARGET version."

DOCKER_COMPOSE_FILE="$RUN_TARGET-deploy.yml"
sudo docker-compose -f "$DOCKER_COMPOSE_FILE" pull || { ERR_MSG='Failed to pull docker image'; exit 1; }
sudo docker-compose -f "$DOCKER_COMPOSE_FILE" up -d || { ERR_MSG='Failed to start docker image'; exit 1; }
sleep 50

#echo "Starting health check for the new version of the application."
#
#HEALTH_CHECK_PASSED=true
#RUN_CONTAINER_IDS=$(sudo docker ps --filter "name=$RUN_TARGET" --quiet --all)
#
#for CONTAINER_ID in $RUN_CONTAINER_IDS; do
#  HEALTH_STATUS=$(sudo docker inspect --format "{{.State.Health.Status}}" $CONTAINER_ID)
#  if [ "$HEALTH_STATUS" != "healthy" ]; then
#    HEALTH_CHECK_PASSED=false
#    break
#  fi
#done
#
#if [ "$HEALTH_CHECK_PASSED" = false ]; then
#  sudo docker image prune -af
#  ERR_MSG="Health check failed."
#  exit 1
#fi
#
#echo "Health check passed. Reloading nginx to transfer traffic from $STOP_TARGET to $RUN_TARGET."

NGINX_ID=$(sudo docker ps --filter "name=nginx" --quiet)
NGINX_CONFIG="/etc/nginx/conf.d/default.conf"

sudo docker exec $NGINX_ID /bin/bash -c "sed -i 's/$STOP_TARGET:8080/$RUN_TARGET:8080/' $NGINX_CONFIG"
sudo docker exec $NGINX_ID /bin/bash -c "nginx -s reload" || { ERR_MSG='Failed to reload nginx'; exit 1; }

echo "Terminating the $STOP_TARGET applications."

STOP_CONTAINER_ID=$(sudo docker ps --filter "name=$STOP_TARGET" --quiet)
if [ -n "$STOP_CONTAINER_ID" ]; then
  sudo docker stop $STOP_CONTAINER_ID
  sudo docker rm $STOP_CONTAINER_ID
  sudo docker system prune -a
fi

rm .env

echo "Deployment success."
exit 0
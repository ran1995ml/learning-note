#!/bin/bash

NAMESPACE=druid-sample

kubectl get pods -n $NAMESPACE
PODS=$(kubectl get pods -n $NAMESPACE -o jsonpath='{.items[*].metadata.name}')
HISTORICAL_LOG=var/logs/historical
MIDDLEMANAGER_LOG=/mnt/var/sv/middleManager/current
BROKER_LOG=/mnt/var/sv/broker/current
COORDINATOR_LOG=/mnt/var/sv/coordinator/current
OVERLORD_LOG=/mnt/var/sv/overlord/current

MSG="ERROR"

for POD in $PODS; do
  if [[ $POD =~ data ]]; then
    HISTORICAL_ERROR=$(kubectl exec $POD -n $NAMESPACE -- cat $HISTORICAL_LOG | grep $MSG |wc -l)
    MIDDLEMANAGER_ERROR=$(kubectl exec $POD -n $NAMESPACE -- cat $MIDDLEMANAGER_LOG | grep $MSG |wc -l)
    echo "Processing Pod: $POD, Historical Error Count: $HISTORICAL_ERROR"
    echo "Processing Pod: $POD, MiddleManager Error Count: $MIDDLEMANAGER_ERROR"
  elif [[ $POD =~ query ]]; then
    BROKER_ERROR=$(kubectl exec $POD -n $NAMESPACE -- cat $BROKER_LOG | grep $MSG |wc -l)
    echo "Processing Pod: $POD, Broker Error Count: $BROKER_ERROR"
  elif [[ $POD =~ master ]]; then
    COORDINATOR_ERROR=$(kubectl exec $POD -n $NAMESPACE -- cat $COORDINATOR_LOG | grep $MSG |wc -l)
    OVERLORD_ERROR=$(kubectl exec $POD -n $NAMESPACE -- cat $OVERLORD_LOG | grep $MSG |wc -l)
    echo "Processing Pod: $POD, Coordinator Error Count: $COORDINATOR_ERROR"
    echo "Processing Pod: $POD, Overlord Error Count: $OVERLORD_ERROR"
  fi
done

#!/bin/bash

NAMESPACE=druid-sample
CLUSTER_NAME=mariadb-galera
CONFIGMAP_NAME=mariadb-galera-configuration


#while true; do
#    ready_replicas=$(kubectl get statefulset $CLUSTER_NAME -n $NAMESPACE -o jsonpath='{.status.readyReplicas}')
#    replicas=$(kubectl get statefulset $CLUSTER_NAME -n $NAMESPACE -o jsonpath='{.status.replicas}')
#
#    if [[ "$ready_replicas" == "$replicas" && "$ready_replicas" != "0" ]]; then
#        echo "All replicas are ready."
#        break
#    else
#        echo "Waiting for all replicas to be ready..."
#        sleep 10
#    fi
#done


echo "Updating config."
CONFIG=$(kubectl get configmap $CONFIGMAP_NAME -n $NAMESPACE -o jsonpath='{.data.my\.cnf}' | \
sed 's|wsrep_cluster_address=gcomm://|wsrep_cluster_address=gcomm://mariadb-galera-0.mariadb-galera-headless.druid-sample.svc.cluster.local|' | \
tr -d '\n')

CONFIG_ESCAPED=$(echo "$CONFIG" | sed 's/"/\\"/g; s/$/\\n/;')

kubectl patch configmap $CONFIGMAP_NAME -n $NAMESPACE --type merge -p "{\"data\":{\"my.cnf\":\"$CONFIG_ESCAPED\"}}"

#echo "Redeploying mariadb galera."
#kubectl rollout restart statefulset $CLUSTER_NAME -n $NAMESPACE

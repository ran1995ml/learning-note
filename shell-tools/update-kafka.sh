#!/bin/bash

TOPICS="
ad_experience.session_summaries.PT1M.1
"

KAFKA=/Users/rwei/Downloads/kafka_2.12-3.1.2/bin
SERVER=rccp109-5d.iad4.prod.conviva.com:30200


for t in $TOPICS;do
  TOPIC=`echo $t | cut -d ',' -f 1`
#  bash $KAFKA/kafka-configs.sh --bootstrap-server $SERVER --alter --entity-type topics --entity-name $TOPIC --add-config "retention.ms=10800000,retention.bytes=10737418240"
  bash $KAFKA/kafka-topics.sh --bootstrap-server $SERVER --alter --topic $TOPIC --partitions 3
  bash $KAFKA/kafka-topics.sh --bootstrap-server $SERVER --describe --topic $TOPIC
  sleep 3
done

#bash $KAFKA/kafka-topics.sh --bootstrap-server $SERVER --list|grep pii

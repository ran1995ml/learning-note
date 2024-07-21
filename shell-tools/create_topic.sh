#!/bin/bash
# The format of these topic names do not meet RFC-1123, therefore can't create
# with Strimzi's KafkaTopic resource.

# topic_name, partitions, replication_factor, retention_ms, retention_bytes
TOPICS="ad_experience.session_summaries.PT1M.14,1,2,
experience_insights.session_summaries.PT1M.14,300,2,
pii.session_summaries.PT1M.14,50,2"
#TOPICS="ad_experience.session_summaries.PT1M.1,15,3,10800000,10737418240
#ad_experience.session_summaries.PT1M.10,1,3,10800000,10737418240
#ad_experience.session_summaries.PT1M.11,1,3,10800000,10737418240
#ad_experience.session_summaries.PT1M.12,6,3,10800000,10737418240
#ad_experience.session_summaries.PT1M.13,30,3,10800000,10737418240
#ad_experience.session_summaries.PT1M.14,1,3,10800000,10737418240
#ad_experience.session_summaries.PT1M.15,1,3,10800000,10737418240
#ad_experience.session_summaries.PT1M.16,10,3,10800000,10737418240
#ad_experience.session_summaries.PT1M.2,3,3,10800000,10737418240
#ad_experience.session_summaries.PT1M.3,1,3,10800000,10737418240
#ad_experience.session_summaries.PT1M.4,1,3,10800000,10737418240
#ad_experience.session_summaries.PT1M.5,1,3,10800000,10737418240
#ad_experience.session_summaries.PT1M.6,1,3,10800000,10737418240
#ad_experience.session_summaries.PT1M.7,10,3,10800000,10737418240
#ad_experience.session_summaries.PT1M.8,1,3,10800000,10737418240
#ad_experience.session_summaries.PT1M.9,60,3,10800000,10737418240
#experience_insights.session_summaries.PT1M.1,165,3,10800000,10737418240
#experience_insights.session_summaries.PT1M.10,50,3,10800000,10737418240
#experience_insights.session_summaries.PT1M.11,75,3,10800000,10737418240
#experience_insights.session_summaries.PT1M.12,25,3,10800000,10737418240
#experience_insights.session_summaries.PT1M.13,300,3,10800000,10737418240
#experience_insights.session_summaries.PT1M.14,300,3,10800000,10737418240
#experience_insights.session_summaries.PT1M.15,50,3,10800000,10737418240
#experience_insights.session_summaries.PT1M.16,100,3,10800000,10737418240
#experience_insights.session_summaries.PT1M.2,50,3,10800000,10737418240
#experience_insights.session_summaries.PT1M.3,25,3,10800000,10737418240
#experience_insights.session_summaries.PT1M.4,25,3,10800000,10737418240
#experience_insights.session_summaries.PT1M.5,15,3,10800000,10737418240
#experience_insights.session_summaries.PT1M.6,30,3,10800000,10737418240
#experience_insights.session_summaries.PT1M.7,30,3,10800000,10737418240
#experience_insights.session_summaries.PT1M.8,1,3,10800000,10737418240
#experience_insights.session_summaries.PT1M.9,360,3,10800000,10737418240
#jobcompleteflag-tlb,1,3,604800000,51539607552
#jobcompleteflag_repair_timeline,1,3,604800000,51539607552
#pii.session_summaries.PT1M.1,25,3,10800000,10737418240
#pii.session_summaries.PT1M.10,6,3,10800000,10737418240
#pii.session_summaries.PT1M.11,15,3,10800000,10737418240
#pii.session_summaries.PT1M.12,2,3,10800000,10737418240
#pii.session_summaries.PT1M.13,50,3,10800000,10737418240
#pii.session_summaries.PT1M.14,50,3,10800000,10737418240
#pii.session_summaries.PT1M.15,10,3,10800000,10737418240
#pii.session_summaries.PT1M.16,10,3,10800000,10737418240
#pii.session_summaries.PT1M.2,6,3,10800000,10737418240
#pii.session_summaries.PT1M.3,2,3,10800000,10737418240
#pii.session_summaries.PT1M.4,2,3,10800000,10737418240
#pii.session_summaries.PT1M.5,5,3,10800000,10737418240
#pii.session_summaries.PT1M.6,5,3,10800000,10737418240
#pii.session_summaries.PT1M.7,5,3,10800000,10737418240
#pii.session_summaries.PT1M.8,1,3,10800000,10737418240
#pii.session_summaries.PT1M.9,30,3,10800000,10737418240"

KAFKA=/Users/rwei/Downloads/kafka_2.12-3.1.2/bin
SERVER=druid-kafka-samp-1.us-central1.prod.gcp.conviva.com:31311


for t in $TOPICS;do
  TOPIC=`echo $t | cut -d ',' -f 1`
  PARTITIONS=`echo $t | cut -d ',' -f 2`
  REP_FACTOR=`echo $t | cut -d ',' -f 3`
  RETENTION_MS=`echo $t | cut -d ',' -f 4`
  RETENTION_BYTES=`echo $t | cut -d ',' -f 5`
  bash $KAFKA/kafka-topics.sh --bootstrap-server $SERVER --create --topic $TOPIC --partitions $PARTITIONS --replication-factor $REP_FACTOR
  bash $KAFKA/kafka-configs.sh --bootstrap-server $SERVER --alter --entity-type topics --entity-name $TOPIC --add-config 'compression.type=gzip,segment.bytes=536870912,retention.ms=7200000,retention.bytes=10737418240'
done

#bash $KAFKA/kafka-topics.sh --bootstrap-server $SERVER --list

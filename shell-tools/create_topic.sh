#!/bin/bash

# topic_name, partitions, replication_factor, segment_bytes, retention_ms, retention_bytes
TOPICS="ad_experience.session_summaries.PT1M.13,10,2,536870912,7200000,10737418240
ad_experience.session_summaries.PT1M.14,10,2,536870912,7200000,10737418240
ad_experience.session_summaries.PT1M.15,10,2,536870912,7200000,10737418240
experience_insights.session_summaries.PT1M.13,300,2,536870912,7200000,10737418240
experience_insights.session_summaries.PT1M.14,300,2,536870912,7200000,10737418240
experience_insights.session_summaries.PT1M.15,300,2,536870912,7200000,10737418240
pii.session_summaries.PT1M.13,10,2,536870912,7200000,10737418240
pii.session_summaries.PT1M.14,10,2,536870912,7200000,10737418240
pii.session_summaries.PT1M.15,10,2,536870912,7200000,10737418240
experience_insights.timelines.nbcu.PT1M.1,10,2,536870912,7200000,10737418240
experience_insights.timelines.nbcu.PT1M.2,10,2,536870912,7200000,10737418240
experience_insights.timelines.nbcu_d2c.PT1M.1,10,2,536870912,7200000,10737418240
tlb-rt-ad-sess-summary-nbcu,10,2,209715200,1200000,1073741824
tlb-rt-ad-sess-summary-nbcu-2,10,2,209715200,1200000,1073741824
tlb-rt-ad-sess-summary-nbcu-d2c,30,2,209715200,1200000,1073741824
tlb-rt-sess-summary-nbcu,30,2,209715200,1200000,1073741824
tlb-rt-sess-summary-nbcu-2,10,2,209715200,1200000,1073741824
tlb-rt-sess-summary-nbcu-d2c,30,2,209715200,1200000,1073741824
tlb-rt-custom-sess-summary-nbcu,10,2,209715200,1200000,1073741824
tlb-rt-custom-sess-summary-nbcu-2,10,2,209715200,1200000,1073741824
tlb-rt-custom-sess-summary-nbcu-d2c,10,2,209715200,1200000,1073741824"

KAFKA=/Users/rwei/Downloads/kafka_2.12-3.1.2/bin
SERVER=druid-kafka-samp-1.us-central1.prod.gcp.conviva.com:31311


for t in $TOPICS;do
  TOPIC=`echo $t | cut -d ',' -f 1`
  PARTITIONS=`echo $t | cut -d ',' -f 2`
  REP_FACTOR=`echo $t | cut -d ',' -f 3`
  SEGMENT_BYTES=`echo $t | cut -d ',' -f 4`
  RETENTION_MS=`echo $t | cut -d ',' -f 5`
  RETENTION_BYTES=`echo $t | cut -d ',' -f 6`
#  bash $KAFKA/kafka-topics.sh --bootstrap-server $SERVER --create --topic $TOPIC --partitions $PARTITIONS --replication-factor $REP_FACTOR
  bash $KAFKA/kafka-configs.sh --bootstrap-server $SERVER --alter --entity-type topics --entity-name $TOPIC --add-config "compression.type=gzip,segment.bytes=$SEGMENT_BYTES,retention.ms=$RETENTION_MS,retention.bytes=$RETENTION_BYTES"
done

bash $KAFKA/kafka-topics.sh --bootstrap-server $SERVER --list

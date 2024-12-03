from prometheus_client import CollectorRegistry, Gauge, push_to_gateway
import random
import time
import logging

PUSHGATEWAY_URL = "https://pushgateway.prod.conviva.com:443"

registry = CollectorRegistry()

gauge1 = Gauge('ad_experience_realtime_itv_1_lag', 'exception flag for api loge', registry=registry)
gauge2 = Gauge('ad_experience_realtime_itv_1_demo_lag', 'exception flag for api loge', registry=registry)

while True:
    value1 = random.randint(500, 2000)
    value2 = random.randint(10, 100)
    gauge1.set(value1)
    gauge2.set(value2)
    logging.info("ad_experience_realtime_itv_1 lag: {}".format(value1))
    logging.info("ad_experience_realtime_itv_1_demo lag: {}".format(value2))
    push_to_gateway(PUSHGATEWAY_URL, job="realtime-lag-test", registry=registry)
    time.sleep(60)

from prometheus_client import CollectorRegistry, Gauge, push_to_gateway

PUSHGATEWAY_URL = "https://pushgateway.prod.conviva.com:443"

registry = CollectorRegistry()

# gauge = Gauge('api_log_count', 'total number of ingested api log data in one s3 file', registry=registry)

gauge = Gauge('api_log_exception_test', 'exception flag for api loge', registry=registry)
# gauge.set(5)
#
# push_to_gateway(PUSHGATEWAY_URL, job="ei-api-log", registry=registry)

try:
    print("hello")
except Exception as e:
    gauge.set(1)
    push_to_gateway(PUSHGATEWAY_URL, job="ei-api-log", registry=registry)

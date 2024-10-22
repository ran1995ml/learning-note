import requests
import urllib3
import matplotlib.pyplot as plt
import matplotlib.ticker as mtick
from matplotlib.ticker import FuncFormatter
from datetime import datetime, timedelta

urllib3.disable_warnings(urllib3.exceptions.InsecureRequestWarning)

HEADERS = {
    'Content-Type': 'application/json'
}

FRONT_URL = "https://druid.conviva-druid-timeline.rke-storage.iad7.prod.conviva.com/druid/coordinator/v1/metadata/datasources/experience_insights.session_summaries.PT1M.7/segments"

UTF_URL = "http://ondemand-druid.iad1.prod.conviva.com:8888/druid/coordinator/v1/metadata/datasources/experience_insights.session_summaries.PT1M.7/segments"

def to_percent(temp, position):
    return '%1.0f%%' % (temp * 100)

def compute_avg_size(intervals, url):
    response = requests.post(url, headers=HEADERS, json=intervals, params={'full': 'true'}, verify=False)
    segments = response.json()
    total_size = 0
    for segment in segments:
        total_size = total_size + segment["size"]
    if len(segments) == 0:
        return 0
    else:
        return total_size

def analysis_by_hour(start_date, end_date):
    front_result = []
    utf_result = []
    diff = []
    date = []
    current_date = start_date
    while current_date <= end_date:
        next_date = current_date + timedelta(hours=1)
        intervals = ["{}/{}".format(current_date.strftime('%Y-%m-%dT%H:%M:%S.000Z'), next_date.strftime('%Y-%m-%dT%H:%M:%S.000Z'))]
        front_avg_size = compute_avg_size(intervals, FRONT_URL)
        utf_avg_size = compute_avg_size(intervals, UTF_URL)
        date.append(current_date.strftime('%Y%m%dT%H'))
        front_result.append(front_avg_size)
        utf_result.append(utf_avg_size)
        diff.append((utf_avg_size - front_avg_size) / utf_avg_size)
        current_date = next_date
    return (date, front_result, utf_result, diff)


def analysis_by_day(start_date, end_date):
    front_result = []
    utf_result = []
    diff = []
    date = []
    current_date = start_date
    while current_date <= end_date:
        next_date = current_date + timedelta(days=1)
        intervals = ["{}/{}".format(current_date.strftime('%Y-%m-%dT%H:%M:%S.000Z'), next_date.strftime('%Y-%m-%dT%H:%M:%S.000Z'))]
        front_size = compute_avg_size(intervals, FRONT_URL)
        utf_size = compute_avg_size(intervals, UTF_URL)
        date.append(current_date.strftime('%Y%m%d'))
        front_result.append(front_size)
        utf_result.append(utf_size)
        diff.append((utf_size - front_size) / utf_size)
        current_date = next_date
    return (date, front_result, utf_result, diff)


start_date = datetime.strptime('2024-10-10T00:00:00.000Z', '%Y-%m-%dT%H:%M:%S.000Z')
end_date = datetime.strptime('2024-10-14T00:00:00.000Z', '%Y-%m-%dT%H:%M:%S.000Z')
result = analysis_by_hour(start_date, end_date)
date = result[0]
diff = result[3]
plt.plot(date, diff, label='Storage saved by Front compared to Utf-8, 2024-10-10T00:00:00.000Z to 2024-10-14T00:00:00.000Z', color='blue')
plt.gca().yaxis.set_major_formatter(FuncFormatter(to_percent))
plt.title("Storage saved by Front compared to Utf-8, 2024-10-10T00:00:00.000Z~2024-10-14T00:00:00.000Z")
plt.xlabel("hour")
plt.ylabel("difference")
ax = plt.gca()
ax.xaxis.set_ticks([])
plt.show()

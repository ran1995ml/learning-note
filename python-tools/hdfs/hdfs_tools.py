from hdfs import InsecureClient

client = InsecureClient('http://rccp106-10b.sjc2.prod.conviva.com:50070')
files = client.list('/druid/timeline/deepstorage/indexing-logs')

print(files[:10])

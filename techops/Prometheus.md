## 数据模型
基于pull模型的架构方式，对一些复杂的情况，还可使用服务发现能力动态管理监控目标
采集的监控数据以指标形式保存在内置的时序数据库TSDB中，除指标名外，还包含一组用于描述该样本特征的标签
每条时间序列由一组标签和指标名唯一标识
单实例Prometheus处理任务量过大，可使用功能分区(sharding)+联邦集群(federation)进行扩展

## 数据采集
Prometheus Server不直接服务监控特定的目标，主要任务是负责数据的收集，存储并对外提供数据查询支持。
监控的工作由Exporter来做，Prometheus周期性从Exporter暴露的HTTP服务地址拉取监控样本数据。
Exporter是一个开放的概念，可以是一个独立运行的程序独立于监控目标，也可以是直接内置在监控目标中，只要能向Prometheus提供标准格式监控样本即可。
每一个暴露监控样本数据的HTTP服务称为一个instance。一组用于相同采集目的的实例，或者同一个采集进程的多个副本通过一个job管理
除了静态配置每一个job的采集instance外，还支持与DNS，K8S集成实现自动发现instance，从这些instance上获取监控数据

docker部署的prometheus，prometheus数据源导入到grafana，地址需要使用容器的地址


## 基础架构
### Prometheus Server
负责对监控数据的获取、存储及查询。可通过静态配置管理监控目标，也可通过Service Discovery的方式动态管理监控目标，从监控目标获取数据。存储采集到的监控数据。
Prometheus Server本身是一个时序数据库，采集到的监控数据按时间序列存储在本地磁盘。
可以从其他Prometheus Server实例中获取数据，在大规模监控情况下，可通过联邦集群及功能分区的方式对Prometheus Server扩展。

### Exporters
Prometheus Server通过访问该Exporter提供的Endpoint，获取需要采集的监控数据。
分为两类，直接采集，直接内置了对Prometheus监控的支持；间接采集，原有监控目标并不支持Prometheus，通过Prometheus提供的Client Lib编写采集程序。

### AlertManager
Prometheus Server可通过PromQL定义的规则产生一条告警，后续处理流程由AlertManager管理
配置包含route和receivers，所有告警都从配置中的顶级route进入路由树，根据路由规则将告警信息发送给相应的receivers。
可以定义一组receivers，比如按角色划分多个，可关联邮件，Slack等
route是一个基于标签匹配规则的树状结构，所有告警从顶级路由开始，根据标签匹配规则进入到不同的子路由，根据子路由设置的接收器发告警。



### PushGateway
Prometheus数据采集基于pull模型设计，网络环境必须要让Prometheus Server能与Exporter直接通信。如果网络无法满足需求，使用PushGateway中转，将内部网络的监控数据主动Push到Gateway。


## 时间序列
node_cpu{cpu="cpu0",mode="idle"} 362812.7890625
非#开头的每一行表示Node Exporter采集到的一个监控样本，node_cpu表示当前指标的名称，大括号的标签反映了当前样本的一些特征维度
Prometheus会将采集到的样本数据以时间序列的方式保存在内存数据库中，定时保存到硬盘上。时间序列按时间戳和值的序列顺序存在，称为向量。
每条时间序列通过指标名称和一组标签集命名，可理解为一个以时间为Y轴的数字矩阵。
样本由指标、时间戳和样本值三部分组成。

## 指标类型
Counter，只增不减的计数器，记录某些事件发生的次数；Gauge，可增可减的仪表盘，反映系统的当前状态；Histogram和Summary用于统计和分析样本的分布情况。
PromQL支持正则表达式，匹配用~，不匹配用!~，多个表达式用|分离


## Exporter
所有可以向Prometheus提供监控样本数据的程序都可以称为Exporter，Exporter的实例称为target。Prometheus通过轮询方式定期从这些target获取样本数据。


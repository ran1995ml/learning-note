# Kubernetes

## 架构

![image-20240108180053603](/Users/rwei/Library/Application Support/typora-user-images/image-20240108180053603.png)

![image-20240102175850907](/Users/rwei/Library/Application Support/typora-user-images/image-20240102175850907.png)

### Control Plane

相当于 `Master`，主要工作：资源调度、监测响应集群事件。

- `kube-apiserver`：处理接受请求，可部署多个实例进行扩缩。
- `etcd`：`raft` 算法实现的键值数据库，存储 `Kubernetes` 的所有集群数据。
- `kube-scheduler`：负责资源的调度，选择 `node` 运行 `pod`。
- `kube-controller-manager`：维护集群状态，负责故障检测、自动扩展、滚动更新。可以运行多个控制器，为降低复杂度，会运行在同一个进程中。包括：
    - `Node Controller`：`Node` 出现故障时通知响应。
    - `Job Controller`：管理 `Job` 对象。
    - `EndpointSlice Controller`：提供 `service` 和 `pod` 间的连接。

### Node

提供运行环境，维护运行的 `pod` 。

- `kubelet`：接收 `podSpec` 运行 `pod`，只管理 `kubernetes` 创建的容器，管理网络和 `Volumn`。
- `kube-proxy`：每个节点上的网络代理，做流量转发，实现 `service` 的功能，维护集群内外部与 `pod` 通信。
- `container-runtime`：管理 `kubernetes` 中容器的执行和生命周期。

`Kubernetes` 会在内部创建一个 `Node` 对象表示节点，由 `Node Controller` 管理。`Node` 会定时发送心跳帮助集群确定每个 `Node`
的可用性。

## API对象

`Kubernetes` 对象是持久化的实体，表示整个集群的状态，描述了以下信息：

- 哪些容器在哪些节点上运行
- 应用可以使用的资源
- 应用运行时的表现，如重启、升级和容错策略

创建 `kubernetes` 对象的必需字段

- `apiVersion`：`Kubernetes Api` 的版本
- `kind`：要创建的对象类别
- `metadata`：对象的唯一标识，至少包含 `namespace`、`name` 和 `uid`，还可以添加 `labels` 和 `annotations`。
    - `namespace`：将同一集群中的资源划分为相互隔离的组。
    - `labels` ：附加在对象上的键值对，指定对用户有意义的标识属性，用于选择对象的子集。对系统本身没有什么含义，只对用户有意义。
    - `annotations`：和 `labels` 相比可以存更多的数据，不用于标识和选择对象。
- `spec`：期望的对象状态描述

```yaml
apiVersion: v1
kind: Pod
metadata:
  name: nginx-demo
spec:
  containers:
  - name: nginx
    image: nginx:1.14.2
    ports:
    - containerPort: 80
```

### 部署

#### Pod

是 `Kubernetes` 中最小的计算单元。管理一组容器，这些容器共享存储、网络，每个 `pod` 都有一个独立的 `ip`。

启动 `pod` ：

![image-20240130175524992](/Users/rwei/Library/Application Support/typora-user-images/image-20240130175524992.png)

删除 `pod` ：

1. 用户发送删除命令，`pod` 超过宽限期后 `kube-apiserver` 更新 `pod` 的状态为 `dead`；
2. 客户端命令行上显示的 `pod` 状态为 `terminating`；
3. `kubelet` 发现 `pod` 被标记为 `terminating` 时，开始停止 `pod` 进程，若定义了 `preStop hook`，停止 `pod`
   前会被调用，之后向 `pod` 中的进程发送 `TERM` 信号删除掉；
4. `kubelet` 向 `kube-apiserver` 返回结果，完成删除。

诊断 `pod` 里的容器：

探针是由 `kubelet` 对容器执行的定期诊断，执行诊断 `kubelet` 会调用由容器实现的 `Handler`。两类探针：

- `livenessProbe`：指示容器是否正在运行，探测失败 `kubelet` 会杀死容器；
- `readinessProbe`：指示容器是否准备好服务请求，探测失败会从 `Service` 中删除该 `pod` 的 `IP` 。

```yaml
livenessProbe:
  initialDelaySeconds: 60
  httpGet:
    path: /status/health
    port: 8083
readinessProbe:
  initialDelaySeconds: 60
  httpGet:
    path: /status/health
    port: 8083
```

#### ReplicaSet

保证一定数量的 `pod` 正常运行。

#### Deployment

建立在 `ReplicaSet` 之上，实现了 `pod` 的滚动更新，是一种控制器，声明式地管理 `pod` 的部署和更新。

```yaml
strategy:
  type: RollingUpdate
  rollingUpdate:
    maxUnavailable: 1
    maxSurge: 1
```

无状态，生成的 `pod` 名称随机：

```
druid-master-547bcbd6cc-mf7cw
```

#### StatefulSet

管理有状态的服务，每个 `pod` 名字事先确定，且挂载自己独立的存储。将确定的 `pod` 和确定的存储关联起来保证状态的连续性。

```
NAME                            READY   STATUS    RESTARTS      AGE
druid-data-tier1-0              2/2     Running   0             2d
druid-data-tier1-1              2/2     Running   0             2d
druid-data-tier2-0              2/2     Running   0             2d
druid-data-tier2-1              2/2     Running   0             2d
```

#### Job

控制批处理型任务，管理的 `pod` 在任务完成后自动退出。

#### nodeSelector

最简单的调度的调度策略，根据`label` 决定 `pod` 分配到哪个 `node` 上，

#### Taint

定义 `node` 和 `pod` 间的互斥关系，避免 `pod` 被分配到不合适的节点。`node` 上有 `taint`，`pod` 必须容忍 `node`
上的全部 `taint` 才能被调度在上面。

#### Affinity

定义 `node` 和 `pod` 间的相吸关系，相比于 `nodeSelector`，可实现更复杂的调度规则：

- `nodeAffinity`：定义 `node` 和 `pod` 的亲和性；
- `podAffinity`：定义 `pod` 和 `pod` 的亲和性；
- `podAntiAffinity`：定义 `pod` 和 `pod` 的反亲和性；

### 访问

#### Service

定义一组 `pod` 的访问方式和网络规则，为应用程序提供了网络层面的抽象，提供统一的访问入口，根据 `label`
确定访问请求应转发到哪些 `pod` 上。

- `ClusterIP`：默认类型，分配一个 `clusterIP`；

- `Headless Service`：设置 `clusterIP:None`，在 `DNS` 中注册了所有 `pod` 地址

  ```
  <pod-name>.<service-name>.<namespace>.svc.cluster.local
  ```

  ```
  - "druid.cache.l2.cluster.nodes=redis-0.redis-headless.druid-demo.svc.cluster.local:6379,redis-1.redis-headless.druid-demo.svc.cluster.local:6379,redis-2.redis-headless.druid-demo.svc.cluster.local:6379,redis-3.redis-headless.druid-demo.svc.cluster.local:6379,redis-4.redis-headless.druid-demo.svc.cluster.local:6379,redis-5.redis-headless.druid-demo.svc.cluster.local:6379"
  ```

```
NAME                      TYPE        CLUSTER-IP       EXTERNAL-IP   PORT(S)                      AGE
druid-data                ClusterIP   10.212.18.5      <none>        8083/TCP,8091/TCP            2d
druid-master              ClusterIP   10.208.228.231   <none>        8081/TCP,8090/TCP            2d
druid-query               ClusterIP   10.219.48.138    <none>        8082/TCP,8888/TCP            2d
mariadb-galera            ClusterIP   10.221.168.25    <none>        3306/TCP                     2d
mariadb-galera-headless   ClusterIP   None             <none>        4567/TCP,4568/TCP,4444/TCP   2d
redis                     ClusterIP   10.222.176.184   <none>        6379/TCP                     2d
redis-headless            ClusterIP   None             <none>        6379/TCP,16379/TCP           2d
zookeeper                 ClusterIP   10.220.12.114    <none>        2181/TCP,2888/TCP,3888/TCP   2d
zookeeper-headless        ClusterIP   None             <none>        2181/TCP,2888/TCP,3888/TCP   2d
```

#### Ingress

提供集群外部到集群内访问的路由。

### 配置

#### ConfigMap

实现配置和容器的解耦，便于应用配置的修改。将数据保存到键值对中。

```yaml
envFrom:
  - configMapRef:
      name: {{ template "druid.common.configmap.name" . }}
  - configMapRef:
      name: {{ template "druid.middleManagerTier1.configmap.name" . }}
```

#### Secret

存储敏感数据，如密码、令牌。

### 存储

#### EmptyDir

用于临时存储，生命周期和 `pod` 相同

```yaml
volumes:
- name: empty-volume
  emptyDir: {}
```

#### PV

集群中的一块存储，可使用 `StorageClass` 动态制备，生命周期独立于 `pod`。

#### PVC

表示用户对 `pv` 的请求。`pod` 请求节点资源，可申请特定大小的内存；`pvc` 申请 `pv` 资源，可申请特定大小，设置访问模式。

#### StorageClass

定义了 `PV` 的生成过程。

```yaml
apiVersion: storage.k8s.io/v1
kind: StorageClass
metadata:
  name: druid-ssd-storage
  annotations:
    cas.openebs.io/config: |
      - name: StorageType
        value: "hostpath"
      - name: BasePath
        value: /data/openebs
    openebs.io/cas-type: local
provisioner: openebs.io/local
reclaimPolicy: Delete
volumeBindingMode: WaitForFirstConsumer
```

#### HostPath

使用宿主机的文件系统作为一个 `volumn`

```yaml
volumes:  
  - name: local-ssd-0
    hostPath:
      path: /conviva/data/druid/ssd1/0
```

### 权限控制

#### ServiceAccount

给运行在 `Pod` 里的应用使用的身份认证，进程访问 `api-server` 时使用的账号就是 `ServiceAccount`。

#### Role和ClusterRole

表示一组权限的规则，`Role` 的范围是 `namespace`，`ClusterRole` 的范围是整个集群。

#### RoleBinding

将 `Role` 中定义的权限赋给 `ServiceAccount`。

### CustomResourceDefinition

```yaml
apiVersion: "druid.apache.org/v1alpha1"
kind: "Druid"
metadata:
  name: tiny-cluster
spec:
  image: apache/druid:25.0.0
nodes:
  brokers:
```


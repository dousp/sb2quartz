
---
##  配置解释

```
#调度标识名 集群中每一个实例都必须使用相同的名称
org.quartz.scheduler.instanceName: DefaultQuartzScheduler
#ID设置为自动获取 每一个必须不同 
org.quartz.scheduler.instanceId: AUTO
#远程管理相关的配置,全部关闭
org.quartz.scheduler.rmi.export: false
org.quartz.scheduler.rmi.proxy: false
org.quartz.scheduler.wrapJobExecutionInUserTransaction: false

#ThreadPool 实现的类名
org.quartz.threadPool.class: org.quartz.simpl.SimpleThreadPool
#线程数量
org.quartz.threadPool.threadCount: 10
#线程优先级 
org.quartz.threadPool.threadPriority: 5
#自创建父线程
org.quartz.threadPool.threadsInheritContextClassLoaderOfInitializingThread: true

#容许的最大作业延
org.quartz.jobStore.misfireThreshold: 60000
#数据保存方式为持久化
org.quartz.jobStore.class: org.quartz.impl.jdbcjobstore.JobStoreTX
#加入集群
org.quartz.jobStore.isClustered: true
#调度实例失效的检查时间间隔
org.quartz.jobStore.clusterCheckinInterval: 10000

```

---

##  配置示例

```

# 默认或是自己改名字都行
#org.quartz.scheduler.instanceName=MyQuartzScheduler
# 如果使用集群，instanceId必须唯一，设置成AUTO
#org.quartz.scheduler.instanceId = AUTO
org.quartz.scheduler.wrapJobExecutionInUserTransaction = false
org.quartz.threadPool.class = org.quartz.simpl.SimpleThreadPool
# threadCount和threadPriority将以setter的形式注入ThreadPool实例
org.quartz.threadPool.threadCount = 10
org.quartz.threadPool.threadPriority = 5
org.quartz.threadPool.threadsInheritContextClassLoaderOfInitializingThread = true

# 使用自己的配置文件
org.quartz.jobStore.misfireThreshold = 10000
# 设置为TRUE不会出现序列化非字符串类到 BLOB 时产生的类版本问题
org.quartz.jobStore.useProperties = true
org.quartz.jobStore.class = org.quartz.impl.jdbcjobstore.JobStoreTX
org.quartz.jobStore.driverDelegateClass = org.quartz.impl.jdbcjobstore.StdJDBCDelegate
org.quartz.jobStore.isClustered = false
org.quartz.jobStore.tablePrefix = QRTZ_
#org.quartz.dataSource中配置的dataSource名字
org.quartz.jobStore.dataSource = qzDS

org.quartz.dataSource.qzDS.connectionProvider.class = com.dou.xin.quartz.conf.DruidConnectionProvider
org.quartz.dataSource.qzDS.driver = com.mysql.jdbc.Driver
org.quartz.dataSource.qzDS.URL = jdbc:mysql://127.0.0.1:3306/quartz_anls_plus?useUnicode=true&characterEncoding=UTF-8
org.quartz.dataSource.qzDS.user = root
org.quartz.dataSource.qzDS.password = 123456
org.quartz.dataSource.qzDS.maxConnections = 10

```

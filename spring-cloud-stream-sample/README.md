# 说明
该示例是Spring Cloud Stream关于RabbitMQ的简单使用示例。

基于项目[spring-cloud-stream-binder-rabbit](https://github.com/spring-cloud/spring-cloud-stream-binder-rabbit)，配置文件参数可参考该项目[README](https://github.com/spring-cloud/spring-cloud-stream-binder-rabbit/blob/master/README.adoc)

## 主要依赖版本

```
<spring-boot.version>2.2.6.RELEASE</spring-boot.version>
<spring-cloud.version>Hoxton.RELEASE</spring-cloud.version>
```

## 项目结构说明
### bom
maven依赖版本管理项目

### base
基础包。用于给实际生产/消费者依赖。

#### general-base
通用基础包。用于定义一些共享常量及对象。避免使用者重复维护共享内容

#### producer-base
生产者基础包。MQ生产者继承该项目。

#### consumer-base
消费者基础包。MQ消费者继承该项目。

### example
示例项目。具体的代码示例。

#### consumer-example
消费者示例。

#### producer-example
生产者示例。
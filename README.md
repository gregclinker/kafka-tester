****Kafka Tester****

Build a runnable jar

`mvn clean install`

To run a Test

`java -jar kafka-tester-1.0.jar testInput.yaml`

where testInput.yaml looks like:

```yaml
---
cacheProducer: true
repeat: 2
threads: 2
kafkaProperties:
  bootstrap.servers: "172.31.0.6:29092,172.31.0.7:29093,172.31.0.5:29094"
  connections.max.idle.ms: "10000"
  request.timeout.ms": "5000"
  security.protocol: "SSL"
  ssl.truststore.location: "/home/greg/work/kafka-heartbeat/secrets/kafka_truststore.jks"
  ssl.truststore.password: "confluent"
  ssl.keystore.location: "/home/greg/work/kafka-heartbeat/secrets/kafka_keystore.jks"
  ssl.keystore.password: "confluent"
  ssl.key.password: "confluent"
  ssl.endpoint.identification.algorithm: " "
kafkaTests:
  - description: Greg Test 1
    topic: greg-test1
    payload: test message
  - description: Greg Test 2
    topic: greg-test2
    payload: test message
```

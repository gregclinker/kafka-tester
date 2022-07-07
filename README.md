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

Output looks like this

```yaml
startTime: "2022-07-07:23:42"
finishTime: "2022-07-07:23:42"
config:
  cacheProducer: true
  repeat: 2
  threads: 2
  kafkaProperties:
    ssl.keystore.location: "/home/greg/work/kafka-heartbeat/secrets/kafka_keystore.jks"
    bootstrap.servers: "172.31.0.6:29092,172.31.0.7:29093,172.31.0.5:29094"
    request.timeout.ms": "5000"
    security.protocol: "SSL"
    ssl.truststore.location: "/home/greg/work/kafka-heartbeat/secrets/kafka_truststore.jks"
    ssl.keystore.password: "confluent"
    ssl.key.password: "confluent"
    connections.max.idle.ms: "10000"
    ssl.truststore.password: "confluent"
    ssl.endpoint.identification.algorithm: " "
  kafkaTests:
  - description: "Greg Test 1"
    topic: "greg-test1"
    payload: "test message"
  - description: "Greg Test 2"
    topic: "greg-test2"
    payload: "test message"
results:
  Greg Test 1:
    count: 3
    failed: 0
    passed: 3
    tps: "4tps"
    min: "4ms"
    max: "719ms"
    average: "243ms"
    greaterThan1000ms: "0%"
    lessThan1000ms: "100%"
    lessThan500ms: "66%"
    lessThan100ms: "66%"
    lessThan50ms: "66%"
    lessThan10ms: "66%"
    p99: "719ms"
    p90: "719ms"
  Greg Test 2:
    count: 4
    failed: 0
    passed: 4
    tps: "23tps"
    min: "4ms"
    max: "98ms"
    average: "43ms"
    greaterThan1000ms: "0%"
    lessThan1000ms: "100%"
    lessThan500ms: "100%"
    lessThan100ms: "100%"
    lessThan50ms: "50%"
    lessThan10ms: "50%"
    p99: "98ms"
    p90: "98ms"
```

****Kafka Tester****

Build a runnable jar

`mvn clean install`

To run a Test

`java -jar kafka-tester-1.0.jar testInput.yaml`

where testInput.yaml looks like:

```yaml
---
cacheProducer: true
repeat: 200
threads: 10
kafkaProperties:
  bootstrap.servers: "172.18.0.7:29092,172.18.0.6:29093,172.18.0.5:29094"
  connections.max.idle.ms: "10000"
  request.timeout.ms": "120000"
  security.protocol: "SSL"
  ssl.truststore.location: "/home/greg/work/kafka-heartbeat/secrets/kafka_truststore.jks"
  ssl.truststore.password: "confluent"
  ssl.keystore.location: "/home/greg/work/kafka-heartbeat/secrets/kafka_keystore.jks"
  ssl.keystore.password: "confluent"
  ssl.key.password: "confluent"
  ssl.endpoint.identification.algorithm: " "
  client.id: "DemoProducer"
  key.serializer: "org.apache.kafka.common.serialization.StringSerializer"
  value.serializer: "org.apache.kafka.common.serialization.StringSerializer"
  acks: "all"
kafkaTests:
  - description: test-topic2, 200kb, acks=all, SSL
    topic: test-topic2
    # create random text message
    payload: __200kb
  - description: test-topic3, 200kb, acks=all, SSL
    topic: test-topic3
    # create random text message
    payload: __200kb
```

Output looks like this

```yaml
startTime: "2023-06-17:07:55"
finishTime: "2023-06-17:07:56"
config:
  cacheProducer: true
  repeat: 200
  threads: 10
  kafkaProperties:
    bootstrap.servers: "172.18.0.7:29092,172.18.0.6:29093,172.18.0.5:29094"
    key.serializer: "org.apache.kafka.common.serialization.StringSerializer"
    ssl.key.password: "confluent"
    connections.max.idle.ms: "10000"
    ssl.truststore.password: "confluent"
    ssl.endpoint.identification.algorithm: " "
    client.id: "DemoProducer"
    acks: "all"
    ssl.keystore.location: "/home/greg/work/kafka-heartbeat/secrets/kafka_keystore.jks"
    request.timeout.ms": "120000"
    security.protocol: "SSL"
    ssl.truststore.location: "/home/greg/work/kafka-heartbeat/secrets/kafka_truststore.jks"
    value.serializer: "org.apache.kafka.common.serialization.StringSerializer"
    ssl.keystore.password: "confluent"
  kafkaTests:
  - description: "test-topic1, 200kb, acks=all, SSL"
    topic: "test-topic1"
    payload: "__200kb"
  - description: "test-topic2, 200kb, acks=all, SSL"
    topic: "test-topic2"
    payload: "__200kb"
  - description: "test-topic3, 200kb, acks=all, SSL"
    topic: "test-topic3"
    payload: "__200kb"
results:
  test-topic2, 200kb, acks=all, SSL:
    count: 2000
    failed: 0
    passed: 2000
    tps: "37tps"
    min: "3ms"
    max: "1016ms"
    average: "26ms"
    greaterThan1000ms: "0%"
    lessThan1000ms: "99%"
    lessThan500ms: "99%"
    lessThan100ms: "98%"
    lessThan50ms: "94%"
    lessThan10ms: "16%"
    p99: "207ms"
    p90: "38ms"
  test-topic3, 200kb, acks=all, SSL:
    count: 2000
    failed: 0
    passed: 2000
    tps: "30tps"
    min: "3ms"
    max: "1023ms"
    average: "33ms"
    greaterThan1000ms: "0%"
    lessThan1000ms: "99%"
    lessThan500ms: "99%"
    lessThan100ms: "98%"
    lessThan50ms: "91%"
    lessThan10ms: "2%"
    p99: "142ms"
    p90: "47ms"
```
Run a Kafka Local Cluster in Docker Compose

```shell script
# run docker compose
./up.sh

# sources some key Kafka envs
. setKafka.sh

# create test topics
./create-test-topics.sh

# run all the tests and output
./run-all.sh
```

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

Output is like this

```json
{
  "startTime" : "2022-07-07:23:33",
  "finishTime" : "2022-07-07:23:33",
  "config" : {
    "cacheProducer" : true,
    "repeat" : 200,
    "threads" : 20,
    "kafkaProperties" : {
      "ssl.keystore.location" : "/home/greg/work/kafka-heartbeat/secrets/kafka_keystore.jks",
      "bootstrap.servers" : "172.31.0.6:29092,172.31.0.7:29093,172.31.0.5:29094",
      "request.timeout.ms\"" : "5000",
      "security.protocol" : "SSL",
      "ssl.truststore.location" : "/home/greg/work/kafka-heartbeat/secrets/kafka_truststore.jks",
      "ssl.keystore.password" : "confluent",
      "ssl.key.password" : "confluent",
      "connections.max.idle.ms" : "10000",
      "ssl.truststore.password" : "confluent",
      "ssl.endpoint.identification.algorithm" : " "
    },
    "kafkaTests" : [ {
      "description" : "Greg Test 1",
      "topic" : "greg-test1",
      "payload" : "test message"
    }, {
      "description" : "Greg Test 2",
      "topic" : "greg-test2",
      "payload" : "test message"
    } ]
  },
  "results" : {
    "Greg Test 1" : {
      "count" : 3999,
      "failed" : 0,
      "passed" : 3999,
      "tps" : "43tps",
      "min" : "2ms",
      "max" : "2230ms",
      "average" : "23ms",
      "greaterThan1000ms" : "0%",
      "lessThan1000ms" : "99%",
      "lessThan500ms" : "99%",
      "lessThan100ms" : "99%",
      "lessThan50ms" : "98%",
      "lessThan10ms" : "31%",
      "p99" : "64ms",
      "p90" : "23ms"
    },
    "Greg Test 2" : {
      "count" : 4000,
      "failed" : 0,
      "passed" : 4000,
      "tps" : "64tps",
      "min" : "2ms",
      "max" : "469ms",
      "average" : "15ms",
      "greaterThan1000ms" : "0%",
      "lessThan1000ms" : "100%",
      "lessThan500ms" : "100%",
      "lessThan100ms" : "99%",
      "lessThan50ms" : "97%",
      "lessThan10ms" : "29%",
      "p99" : "71ms",
      "p90" : "23ms"
    }
  }
}
```

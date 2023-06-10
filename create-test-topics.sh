#
BOOTSTRAP_SERVER=${KAFKA1}:9092,${KAFKA2}:9093,${KAFKA3}:9094
echo $BOOTSTRAP_SERVER
#
/opensource/kafka_2.13-3.1.0/bin/kafka-topics.sh --bootstrap-server=${BOOTSTRAP_SERVER} --delete --topic test-topic1,test-topic2,test-topic3
#
sleep 10
/opensource/kafka_2.13-3.1.0/bin/kafka-topics.sh --bootstrap-server=${BOOTSTRAP_SERVER} --create --topic test-topic1 --partitions 9 --replication-factor 3 --config min.insync.replicas=3
#
sleep 10
/opensource/kafka_2.13-3.1.0/bin/kafka-topics.sh --bootstrap-server=${BOOTSTRAP_SERVER} --create --topic test-topic2 --partitions 6 --replication-factor 3 --config min.insync.replicas=3
#
sleep 10
/opensource/kafka_2.13-3.1.0/bin/kafka-topics.sh --bootstrap-server=${BOOTSTRAP_SERVER} --create --topic test-topic3 --partitions 3 --replication-factor 3 --config min.insync.replicas=3
#
sleep 30
#

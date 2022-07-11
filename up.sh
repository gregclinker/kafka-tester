export KAFKA_DATA=/home/greg/work/kafka-heartbeat
docker-compose up -d
#
docker inspect -f '{{range.NetworkSettings.Networks}}{{.IPAddress}}{{end}}' kafka1  | awk '{print "export KAFKA1="$1}' > setKafka.sh
docker inspect -f '{{range.NetworkSettings.Networks}}{{.IPAddress}}{{end}}' kafka2  | awk '{print "export KAFKA2="$1}' >> setKafka.sh
docker inspect -f '{{range.NetworkSettings.Networks}}{{.IPAddress}}{{end}}' kafka3  | awk '{print "export KAFKA3="$1}' >> setKafka.sh
docker inspect -f '{{range.NetworkSettings.Networks}}{{.IPAddress}}{{end}}' zoo1  | awk '{print "export ZOO1="$1}' >> setKafka.sh
docker inspect -f '{{range.NetworkSettings.Networks}}{{.IPAddress}}{{end}}' zoo2  | awk '{print "export ZOO2="$1}' >> setKafka.sh
docker inspect -f '{{range.NetworkSettings.Networks}}{{.IPAddress}}{{end}}' zoo3  | awk '{print "export ZOO3="$1}' >> setKafka.sh
#
source ./setKafka.sh
#rm setKafka.sh
#
echo $KAFKA1 | awk '{print $1,"kafka1"}'
echo $KAFKA2 | awk '{print $1,"kafka2"}'
echo $KAFKA3 | awk '{print $1,"kafka3"}'
echo $ZOO1 | awk '{print $1,"zoo1"}'
echo $ZOO2 | awk '{print $1,"zoo2"}'
echo $ZOO3 | awk '{print $1,"zoo3"}'

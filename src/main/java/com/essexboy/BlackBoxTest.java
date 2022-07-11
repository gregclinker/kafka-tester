package com.essexboy;

import lombok.Setter;
import lombok.SneakyThrows;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Properties;
import java.util.concurrent.ExecutionException;

@Setter
public class BlackBoxTest implements Runnable {

    private final int id;
    KafkaProducer producer;
    private Logger logger;
    private Report report;
    private Config config;

    public BlackBoxTest(Config config, Report report, int id) throws Exception {
        this.config = config;
        this.report = report;
        this.id = id;
        logger = LoggerFactory.getLogger(BlackBoxTest.class.getSimpleName() + id);
    }

    @SneakyThrows
    public void run() {
        int count = 0;
        if (config.isCacheProducer()) {
            producer = getProducer();
        }
        for (int i = 0; i < config.getRepeat(); i++) {
            for (KafkaTest kafkaTest : config.getKafkaTests()) {
                count++;
                if (count % config.getSlice() == 0) {
                    System.out.print("*");
                }
                report.add(runTest(kafkaTest, id + "_" + count));
            }
        }
        if (config.isCacheProducer()) {
            producer.close();
        }
    }

    private Result runTest(KafkaTest kafkaTest, String key) throws Exception {
        final long start = System.currentTimeMillis();
        if (!config.isCacheProducer()) {
            producer = getProducer();
        }
        try {
            producer.send(new ProducerRecord<>(kafkaTest.getTopic(),
                    key,
                    kafkaTest.getResolvedPayload() + "_" + key)).get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
            return new Result(kafkaTest.getDescription(), false, System.currentTimeMillis() - start);
        } finally {
            if (!config.isCacheProducer()) {
                producer.close();
            }
        }
        return new Result(kafkaTest.getDescription(), true, System.currentTimeMillis() - start);
    }

    private KafkaProducer<Object, Object> getProducer() {
        final Properties kafkaProperties = config.getKafkaProperties();
        return new KafkaProducer<>(kafkaProperties);
    }
}

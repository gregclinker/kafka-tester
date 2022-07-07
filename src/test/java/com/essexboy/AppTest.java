package com.essexboy;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AppTest {

    private static final Logger logger = LoggerFactory.getLogger(BlackBoxTestRunner.class);

    @Test
    public void config() throws Exception {
        BlackBoxTestRunner blackBoxTestRunner = new BlackBoxTestRunner(getClass().getResourceAsStream("/testInput.yaml"));
        final Config config = blackBoxTestRunner.getConfig();
        KafkaTest kafkaTest1 = config.getKafkaTests().get(0);
        assertEquals("greg-test1", kafkaTest1.getTopic());
        System.out.println(config);
    }

    @Test
    public void run() throws Exception {
        BlackBoxTestRunner blackBoxTestRunner = new BlackBoxTestRunner(getClass().getResourceAsStream("/testInput.yaml"));
        blackBoxTestRunner.runTests();
        blackBoxTestRunner.runReport();
    }
}



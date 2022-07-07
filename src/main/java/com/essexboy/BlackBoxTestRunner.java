package com.essexboy;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import com.fasterxml.jackson.dataformat.yaml.YAMLGenerator;
import lombok.Getter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

@Getter
public class BlackBoxTestRunner {

    final static Logger logger = LoggerFactory.getLogger(BlackBoxTestRunner.class);

    private final Config config;
    private final Report report;
    private String inputFile;

    public BlackBoxTestRunner(String inputFile) throws Exception {
        this(new FileInputStream(inputFile));
        this.inputFile = inputFile;
    }

    public BlackBoxTestRunner(InputStream inputStream) throws Exception {
        ObjectMapper mapper = new ObjectMapper(new YAMLFactory().disable(YAMLGenerator.Feature.WRITE_DOC_START_MARKER));
        mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        config = mapper.readValue(inputStream, Config.class);
        report = new Report(config);
    }

    public void runReport() throws IOException {
        report.report();
    }

    public void runTests() throws Exception {
        logger.debug(config.getSummary());
        List<Thread> threads = new ArrayList<>();
        for (int i = 0; i < config.getThreads(); i++) {
            System.out.print("starting thread " + i + ", Running " + config.getKafkaTests().size() + " tests\n");
            BlackBoxTest blackBoxTest = new BlackBoxTest(config, report, i);
            Thread thread = new Thread(blackBoxTest);
            threads.add(thread);
        }
        System.out.println(config.getProgressBar());
        for (Thread thread : threads) {
            thread.start();
        }
        for (Thread thread : threads) {
            thread.join();
        }
        System.out.println("\nfinished all threads");
    }
}

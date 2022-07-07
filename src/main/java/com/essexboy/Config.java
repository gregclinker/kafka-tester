package com.essexboy;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Properties;

@Getter
@Setter
@ToString
public class Config {

    private boolean cacheProducer;
    private int repeat = 1;
    private int threads = 1;
    private Map<String, String> kafkaProperties;
    private List<KafkaTest> kafkaTests = new ArrayList<>();

    public Properties getKafkaProperties() {
        Properties properties = new Properties();
        properties.putAll(kafkaProperties);
        return properties;
    }

    @JsonIgnore
    public Integer getSlice() {
        int totalTests = repeat * threads * kafkaTests.size();
        int perThreadTests = repeat * kafkaTests.size();
        if (totalTests < 100) {
            return 1;
        } else if (threads > 100) {
            return perThreadTests;
        } else {
            return totalTests / 100;
        }
    }

    @JsonIgnore
    public String getProgressBar() {
        int totalTests = repeat * threads * kafkaTests.size();
        int perThreadTests = repeat * kafkaTests.size();
        int count = 100;
        if (totalTests < 100) {
            count = totalTests;
        } else if (threads > 100) {
            count = threads;
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("|");
        for (int i = 1; i < count - 1; i++) {
            stringBuilder.append("-");
        }
        stringBuilder.append("|");
        return stringBuilder.toString();
    }

    @JsonIgnore
    public String getSummary() {
        return this.toString();
    }
}
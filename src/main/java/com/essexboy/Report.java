package com.essexboy;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Getter;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@Getter
@JsonPropertyOrder({"startTime", "finishTime", "config", "results"})
public class Report {
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private final Map<String, Results> results = new ConcurrentHashMap<>();
    protected Config config;
    protected Date startTime;
    protected Date finishTime;

    public Report(Config config) {
        startTime = Calendar.getInstance().getTime();
        this.config = config;
    }

    public Map<String, Results> getResults() {
        SortedMap<String, Results> sortedMap = new TreeMap<>();
        for (KafkaTest kafkaTest : config.getKafkaTests()) {
            sortedMap.put(kafkaTest.getDescription(), results.get(kafkaTest.getDescription()));
        }
        return sortedMap;
    }

    public void add(Result result) {
        if (results.get(result.getDescription()) == null) {
            results.put(result.getDescription(), new Results());
        }
        results.get(result.getDescription()).add(result);
    }

    public void report() throws IOException {
        finishTime = Calendar.getInstance().getTime();
        ObjectMapper objectMapper = new ObjectMapper();
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd:HH:mm");
        objectMapper.setDateFormat(df);
        System.out.println(objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(this));
    }
}

package com.essexboy;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Getter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Getter
@JsonPropertyOrder({"count", "failed", "passed", "tps", "min", "max", "average", "greaterThan1000ms", "lessThan1000ms", "lessThan500ms", "lessThan100ms", "lessThan50ms", "lessThan10ms", "p99", "p90"})
public class Results {
    @JsonIgnore
    private final List<Result> results = Collections.synchronizedList(new ArrayList<>());

    public void add(Result result) {
        results.add(result);
    }

    public Integer getCount() {
        return results.size();
    }

    public Long getPassed() {
        return results.stream().filter(Result::isPassed).count();
    }

    public Long getFailed() {
        return results.stream().filter(s -> !s.isPassed()).count();
    }

    public String getMax() {
        Long max = 0l;
        for (Result result : results) {
            if (result.getExecutionTime() > max) {
                max = result.getExecutionTime();
            }
        }
        return max + "ms";
    }

    public String getMin() {
        Long min = 100000l;
        for (Result result : results) {
            if (result.getExecutionTime() < min) {
                min = result.getExecutionTime();
            }
        }
        return min + "ms";
    }

    public String getAverage() {
        Long total = 0l;
        for (Result result : results) {
            total += result.getExecutionTime();
        }
        return total / results.size() + "ms";
    }

    public String getTPS() {
        Long total = 0L;
        for (Result result : results) {
            total += result.getExecutionTime();
        }
        return Math.round(results.size() / (total / 1000.0)) + "tps";
    }

    public String getGreaterThan1000ms() {
        return getGreaterThanMs(1000L);
    }

    public String getLessThan1000ms() {
        return getLessThanMs(1000L);
    }

    public String getLessThan500ms() {
        return getLessThanMs(500L);
    }

    public String getLessThan100ms() {
        return getLessThanMs(100L);
    }

    public String getLessThan50ms() {
        return getLessThanMs(50L);
    }

    public String getLessThan10ms() {
        return getLessThanMs(10L);
    }

    public String getP99() {
        return percentile(99) + "ms";
    }

    public String getP90() {
        return percentile(90) + "ms";
    }

    private String getLessThanMs(long time) {
        Integer count = 0;
        for (Result result : results) {
            if (result.getExecutionTime() < time) {
                count++;
            }
        }
        return toPercent(count);
    }

    private String getGreaterThanMs(long time) {
        Integer count = 0;
        for (Result result : results) {
            if (result.getExecutionTime() > time) {
                count++;
            }
        }
        return toPercent(count);
    }

    private String toPercent(Integer count) {
        if (count > 0) {
            return ((Double) ((count.doubleValue() / results.size()) * 100.0)).intValue() + "%";
        }
        return "0%";
    }

    private Long percentile(Integer percentile) {
        Double aDouble = (((double) results.size() / 100.0) * (double) percentile) + 0.5;
        Math.floor(aDouble);
        int limit = aDouble.intValue();
        return results.stream().map(s -> s.getExecutionTime()).sorted().limit(limit).max(Long::compare).get();
    }
}

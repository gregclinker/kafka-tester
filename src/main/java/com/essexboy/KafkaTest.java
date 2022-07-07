package com.essexboy;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({"description", "url", "method", "headers", "body", "expected", "httpTestResult", "good"})
public class KafkaTest {
    private String description;
    private String topic;
    private String payload;

    @JsonIgnore
    public String getSummary() {
        return this.toString();
    }
}

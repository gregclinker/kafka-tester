package com.essexboy;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.RandomStringUtils;

import static java.lang.Integer.parseInt;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({"description", "url", "method", "headers", "body", "expected", "httpTestResult", "good"})
public class KafkaTest {
    private String description;
    private String topic;
    private String payload;
    @JsonIgnore
    private String resolvedPayload;

    public void setPayload(String payload) {
        this.payload = payload;
        if (payload.startsWith("__")) {
            int mesageSize = parseInt(payload.toLowerCase().replaceAll("__", "").replaceAll("kb", "")) * 1000;
            resolvedPayload = RandomStringUtils.random(mesageSize, true, true);
        }
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("KafkaTest{");
        sb.append("description='").append(description).append('\'');
        sb.append(", topic='").append(topic).append('\'');
        sb.append(", payload='").append(payload).append('\'');
        if (resolvedPayload != null && resolvedPayload.length() >= 100) {
            sb.append(", resolvedPayload='").append(resolvedPayload.substring(0, 100)).append("...").append('\'');
        } else {
            sb.append(", resolvedPayload='").append(resolvedPayload).append('\'');
        }
        sb.append('}');
        return sb.toString();
    }
}

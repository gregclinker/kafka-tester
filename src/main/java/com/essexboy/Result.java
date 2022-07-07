package com.essexboy;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@AllArgsConstructor
class Result {
    private String description;
    private boolean passed;
    private long executionTime;
}

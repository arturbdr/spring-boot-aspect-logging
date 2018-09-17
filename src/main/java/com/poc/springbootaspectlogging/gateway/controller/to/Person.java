package com.poc.springbootaspectlogging.gateway.controller.to;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Person {
    private String name;
    private Integer age;
}

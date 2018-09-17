package com.poc.springbootaspectlogging.gateway.controller;

import com.poc.springbootaspectlogging.config.annotation.ControllerLog;
import com.poc.springbootaspectlogging.gateway.controller.to.Person;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE;

@RestController
@RequestMapping("/logged-controller")
public class SampleLoggedControllerB {

    @GetMapping(value = "/sample-get", consumes = APPLICATION_JSON_UTF8_VALUE)
    @ControllerLog
    public ResponseEntity<Person> returnSamplePerson() {
        Person aPerson = Person.builder()
                .age(20)
                .name("Whatever Name For Get")
                .build();
        return ResponseEntity
                .ok(aPerson);
    }

    @PostMapping(value = "/sample-post", consumes = APPLICATION_JSON_UTF8_VALUE, produces = APPLICATION_JSON_UTF8_VALUE)
    @ControllerLog
    public ResponseEntity<Person> returnSampleString(@RequestBody Person person) {
        return ResponseEntity
                .ok(person);
    }
}

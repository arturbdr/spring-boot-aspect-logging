package com.poc.springbootaspectlogging.gateway.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.poc.springbootaspectlogging.gateway.controller.to.Person;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.rule.OutputCapture;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.BDDAssertions.then;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;
import static org.springframework.http.HttpHeaders.CONTENT_TYPE;
import static org.springframework.http.HttpMethod.GET;
import static org.springframework.http.HttpMethod.POST;
import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = RANDOM_PORT)
public class SampleUnloggedControllerATest {

    @Rule
    public OutputCapture outputCapture = new OutputCapture();

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void shouldNotLogAnythingRelatedToPayloadForPost() throws Exception {
        // GIVEN a valid person for Request
        Person aPerson = Person.builder()
                .age(20)
                .name("Whatever Name")
                .build();
        String personForRequest = objectMapper.writeValueAsString(aPerson);

        // AND a valid Body
        HttpHeaders headers = new HttpHeaders();
        headers.add(CONTENT_TYPE, APPLICATION_JSON_UTF8_VALUE);
        HttpEntity<String> httpEntity = new HttpEntity<>(personForRequest, headers);

        // WHEN I try to call consume the post endpoint
        ResponseEntity<Void> responseEntity = testRestTemplate
                .exchange("/unlogged-controller/sample-post", POST, httpEntity, Void.class);

        // THEN there should be no log
        String output = this.outputCapture.toString();
        then(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        then(output).doesNotContain("Whatever Name");
    }

    @Test
    public void shouldNotLogAnythingRelatedToPayloadForGet() {
        // GIVEN a valid REST call

        // AND a valid Body
        HttpHeaders headers = new HttpHeaders();
        headers.add(CONTENT_TYPE, APPLICATION_JSON_UTF8_VALUE);
        HttpEntity<String> httpEntity = new HttpEntity<>(null, headers);

        // WHEN I try to call consume the post endpoint
        ResponseEntity<Void> responseEntity = testRestTemplate
                .exchange("/unlogged-controller/sample-get", GET, httpEntity, Void.class);

        // THEN there should be no log
        String output = this.outputCapture.toString();
        then(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        then(output).doesNotContain("Whatever");
    }
}


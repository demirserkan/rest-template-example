package com.demirserkan.resttemplateexample.controller;

import com.demirserkan.resttemplateexample.domain.Student;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@RestController
@Slf4j
@RequiredArgsConstructor
public class Controller {

    private final RestTemplate restTemplate;
    private static final String URL = "http://localhost:8080/students";

    @GetMapping("/getStudents")
    public List<Student> restCallForStudents() {

        List<Student> result = new ArrayList<>();

        try {
            result = restTemplate.getForObject(URL, ArrayList.class);

            log.info("Student list: " + result);
        } catch (Exception e) {
            log.error("Getting students failed ... error:" + e);
        }
        return result;
    }

    @PostMapping("/addStudent")
    public Student restCallForAddingNewStudent(@RequestBody Student student) {
        try {
            HttpEntity<Student> request = new HttpEntity<>(student);

            log.info("Request is :" + student);

            ResponseEntity<String> result = restTemplate.postForEntity(URL, request, String.class);

            log.info("Adding student complete. Result is " + result);
        } catch (Exception e) {
            log.error("Adding student failed ... error:" + e);
        }
        return student;
    }

    @GetMapping("/getSpecificStudent/{studentNo}")
    public Student restCallForSpecificStudent(@PathVariable Long studentNo) {

        Student result = new Student();
        try {
            String uri = URL + "/" + studentNo.toString();

            log.info("URI is :" + uri);

            result = restTemplate.getForObject(uri, Student.class);

            log.info("Student is: " + result);

        } catch (Exception e) {
            log.error("Getting student failed ... error:" + e);
        }
        return result;
    }
}

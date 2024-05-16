package com.sky.restapi0513.controller;

import com.sky.restapi0513.dto.ResponseDTO;
import com.sky.restapi0513.dto.TestRequestBodyDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/test5")
public class Test2Controller {

    @GetMapping("/testResponseEntity")
    public ResponseEntity<?> testResponseEntity() {
        List<String> list = new ArrayList<>();
        list.add("Hello World!");
        ResponseDTO<String> res = ResponseDTO.<String>builder().data(list).build();
        return ResponseEntity.badRequest().body(res);
    }

    @GetMapping("/testResponseBody")
    public ResponseDTO<String> testResponseBody() {
        List<String> list = new ArrayList<>();
        list.add("Hello World!");
        ResponseDTO<String> res = ResponseDTO.<String>builder().data(list).build();
        return res;
    }

    @GetMapping("/testRequestBody")
    public ResponseEntity<TestRequestBodyDTO> testRequestBody1(@RequestBody TestRequestBodyDTO testRequestBodyDTO) {
        System.out.println(testRequestBodyDTO);
        return ResponseEntity.ok(testRequestBodyDTO);
    }

    @PostMapping("/testRequestBody")
    public TestRequestBodyDTO testRequestBody2(@RequestBody TestRequestBodyDTO testRequestBodyDTO) {
        System.out.println(testRequestBodyDTO);
        return testRequestBodyDTO;
    }

    @GetMapping
    public String test5() {
        return "Hello World";
    }
    @GetMapping("/test6/test7")
    public String test6() {
        return "Hello World6";
    }
}

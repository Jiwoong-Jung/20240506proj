package com.study.springboot.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.study.springboot.dto.User;
import com.study.springboot.dto.UserAddDto;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
public class UserController {

    private List<User> users = new ArrayList<>();
    private int id = 0;

    // User 추가
    @PostMapping("/user")
    @Operation(summary = "Add User", description = "유저 추가")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "통과"),
            @ApiResponse(responseCode = "400", description = "실패")
    })
    public String add(@RequestBody UserAddDto user) {
        users.add( new User(id, user.getNickname(), user.getPassword()) );
        id ++;
        return "User 추가 완료";
    }

    // User 조회
    @GetMapping("/user/{id}")
    public User findById(@PathVariable int id) {
        return users.get(id);
    }

    // User 전체 조회
    @GetMapping("/users")
    public List<User> findAll() {
        return users;
    }
}

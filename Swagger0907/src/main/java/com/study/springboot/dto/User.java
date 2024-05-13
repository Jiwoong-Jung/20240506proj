package com.study.springboot.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@AllArgsConstructor
@ToString
@Setter
@NoArgsConstructor
public class User {
    private int id;
    private String nickname;
    private String password;
}

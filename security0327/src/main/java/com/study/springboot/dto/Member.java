package com.study.springboot.dto;

import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Member {
    private Long id;
    private String mid;
    private String pass;

}

package com.sky._sb0507.controller;

import com.sky._sb0507.spring.TestException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.TypeMismatchException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
@Slf4j
public class TestController {
    @GetMapping("/test/{num}")
    public  String test(@PathVariable("num") int num) {
        log.info("num값:{}", num);
        if (num == 1) {
            return "test/test1";
        } else if (num == 2) {
            return "test/test2";
        } else {
            return "redirect:/test";
        }
    }

    @GetMapping("/test")
    public void test() {
        System.out.println("-----------------test");
        throw new TestException("Test 예외");
//        return "test/test";
    }

//    @ExceptionHandler(TypeMismatchException.class)
//    public String handleTypeMismatchException() {
//        return "member/invalidId";
//    }

    @ExceptionHandler(NumberFormatException.class)
        public String handleNumberFormatException() {
        return "member/invalidId";
    }
}

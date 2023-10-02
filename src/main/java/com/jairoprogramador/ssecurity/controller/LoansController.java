package com.jairoprogramador.ssecurity.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.Map;

@RestController
@RequestMapping("/loans")
public class LoansController {
    @GetMapping
    public Map<String, String> loans() {
        return Collections.singletonMap("msj", "loans");
    }

    @PostMapping
    public Object methodoPostTest() {
        return "hello world they are a test method";
    }
}

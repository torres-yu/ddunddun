package io.torres.practice.controller;

import io.torres.practice.service.HelloService;
import io.torres.practice.vo.response.Response;
import io.torres.practice.vo.response.ResponseBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/hello")
public class HelloController {

    @Autowired
    HelloService helloService;

    @GetMapping("")
    public Response hello() {

        return new ResponseBuilder()
                .add("data", helloService.getEmployee())
                .build();
    }
}

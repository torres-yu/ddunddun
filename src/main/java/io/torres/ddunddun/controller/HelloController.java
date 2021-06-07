package io.torres.ddunddun.controller;

import io.torres.ddunddun.service.HelloService;
import io.torres.ddunddun.vo.response.Response;
import io.torres.ddunddun.vo.response.ResponseBuilder;
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
                .add("emp327", helloService.getEmployee())
                .build();
    }
}

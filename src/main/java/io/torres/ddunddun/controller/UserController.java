package io.torres.ddunddun.controller;

import io.torres.ddunddun.service.ReadService;
import io.torres.ddunddun.vo.response.Response;
import io.torres.ddunddun.vo.response.ResponseBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/user")
public class UserController {

    @Autowired
    private ReadService readService;

    @GetMapping("")
    public Response user() {

        return new ResponseBuilder()
                .add("data", readService.getEmployee())
                .build();
    }
}

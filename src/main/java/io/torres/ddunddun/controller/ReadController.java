package io.torres.ddunddun.controller;

import io.torres.ddunddun.service.ReadService;
import io.torres.ddunddun.vo.response.Response;
import io.torres.ddunddun.vo.response.ResponseBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/read")
public class ReadController {

    @Autowired
    private ReadService readService;

    @GetMapping("")
    public Response read() {

        return new ResponseBuilder()
                .add("data", readService.getEmployee())
                .build();
    }
}

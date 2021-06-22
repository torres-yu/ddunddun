package io.torres.ddunddun.controller;

import io.swagger.annotations.ApiOperation;
import io.torres.ddunddun.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
@RequestMapping("api/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @GetMapping("/login")
    public void oauth2Login(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {

        authService.loginProcessing(request, response, authentication);
    }
}

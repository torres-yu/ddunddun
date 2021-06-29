package io.torres.ddunddun.controller;

import io.torres.ddunddun.service.LoginService;
import io.torres.ddunddun.vo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/")
public class LoginController {

    @Autowired
    private LoginService loginService;

    @GetMapping("")
    public String index() {

        return "/home/index";
    }

    @GetMapping("login")
    public String redirectLogin() {
        return "/member/loginForm";
    }

    @PostMapping("join")
    public Long join(@RequestBody User user) {
        return loginService.join(user);
    }

    @PostMapping("login")
    public String login(@RequestBody User user) {
        return loginService.login(user);
    }
}

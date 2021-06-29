package io.torres.ddunddun.service;

import io.torres.ddunddun.security.TokenAuthenticationService;
import io.torres.ddunddun.util.CommonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Service
public class AuthService {

    @Autowired
    private TokenAuthenticationService tokenAuthenticationService;

    public void loginProcessing(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {

        String url = CommonUtils.getUrl(request);
        try {
            tokenAuthenticationService.addAuthentication(response, url, true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

package io.torres.ddunddun.service;

import io.torres.ddunddun.security.TokenAuthenticationService;
import io.torres.ddunddun.security.TokenProvider;
import org.apache.catalina.security.SecurityUtil;
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

        String url = this.getUrl(request);
        try {
            tokenAuthenticationService.addAuthentication(request, response, url, true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected String getUrl(HttpServletRequest request) {
        return request.getScheme() + "://" +
                request.getServerName() +
                ":" + request.getServerPort();
    }
}

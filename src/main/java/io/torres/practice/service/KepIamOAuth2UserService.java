package io.torres.practice.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.kep.common.security.oauth.entity.KepIamUserInfo;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.client.RestTemplate;

public class KepIamOAuth2UserService implements OAuth2UserService<OAuth2UserRequest, OAuth2User> {

    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper;
    private final String userInfoUri;

    // TODO: 다양한 예외상황에 대한 처리 (org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService 참조)
    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {

        return new DefaultOAuth2User(null, null, "user");
    }
}

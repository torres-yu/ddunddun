package io.torres.ddunddun.config;

import io.torres.ddunddun.entity.KakaoOAuth2UserService;
import io.torres.ddunddun.security.TokenAuthenticationService;
import io.torres.ddunddun.security.filter.JwtAuthenticationFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Order(2)
@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final TokenAuthenticationService tokenAuthenticationService;

    private final KakaoOAuth2UserService kakaoOAuth2UserService;

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.httpBasic()
                .and()
                .authorizeRequests()
                .antMatchers("/admin/**").hasRole("ADMIN")
                .antMatchers("/user/**").hasRole("USER")
                .antMatchers("/**").permitAll()
                .and()
                .addFilterBefore(new JwtAuthenticationFilter(tokenAuthenticationService), UsernamePasswordAuthenticationFilter.class)
                .csrf().disable()
                .oauth2Login()
                .userInfoEndpoint()
                .userService(kakaoOAuth2UserService)
                .and()
                .defaultSuccessUrl("/hello",true)
    }
}

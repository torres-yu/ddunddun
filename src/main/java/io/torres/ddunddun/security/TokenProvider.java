package io.torres.ddunddun.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.torres.ddunddun.exception.detail.UnauthorizedDetail;
import io.torres.ddunddun.exception.UnauthorizedException;
import io.torres.ddunddun.util.JsonUtils;
import io.torres.ddunddun.vo.User;
import net.logstash.logback.encoder.org.apache.commons.lang3.StringUtils;
import org.eclipse.jetty.util.StringUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.token.Sha512DigestUtils;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class TokenProvider {

    @Value("${spring.token.key}")
    private String key;

    @Value("${spring.token.prefix}")
    private String tokenPrefix;

    @Value("${spring.token.jwt.expire}")
    private long jwtExpireDuration;

    @Value("${spring.token.refresh.expire}")
    private long refreshExpireDuration;

    private String secret;

    @PostConstruct
    public void init() {
        secret = Sha512DigestUtils.shaHex(key);
    }

    public String issueToken(User user, String type) {

        long expireDuration;
        if(type.equals("Access"))
            expireDuration = jwtExpireDuration;
        else
            expireDuration = refreshExpireDuration;

        Map<String, Object> claims = new HashMap<>();
        claims.put("user", JsonUtils.objectToJson(user));
        return Jwts.builder()
                .setSubject(Long.toString(user.getEmpId()))
                .setClaims(claims)
                .setExpiration(new Date(System.currentTimeMillis() + expireDuration))
                .signWith(SignatureAlgorithm.HS512, secret)
                .compact();
    }

    public String getToken(HttpServletRequest request, String headerString) {
        String token = request.getHeader(headerString);
        return getToken(token);
    }

    public String getToken(String token) {
        token = StringUtils.replace(token, tokenPrefix, "");
        token = StringUtils.trim(token);
        if (StringUtils.isNotEmpty(token)) {
            return token;
        }

        return null;
    }

    public Claims parseJwt(String token) {
        try {
            return Jwts.parser()
                    .setSigningKey(secret)
                    .parseClaimsJws(token).getBody();
        } catch (ExpiredJwtException expiredException) {
            if (StringUtil.isBlank(token)) {
                return null;
            } else {
                throw new UnauthorizedException("Expired token", UnauthorizedDetail.EXPIRED_TOKEN.value());
            }
        } catch (Exception e) {
            if (StringUtil.isBlank(token)) {
                throw new UnauthorizedException("Token is null", UnauthorizedDetail.TOKEN_IS_NULL.value());
            } else {
                throw new UnauthorizedException("Token parse error", UnauthorizedDetail.TOKEN_PARSE_ERROR.value());
            }
        }
    }

    public HttpStatus parseJwtReturnHttpStatus(String token) {
        try {
            Jwts.parser()
                    .setSigningKey(secret)
                    .parseClaimsJws(token).getBody();
            return HttpStatus.OK;
        } catch (ExpiredJwtException expiredException) {
            return HttpStatus.UNAUTHORIZED;
        } catch (Exception e) {
            return HttpStatus.UNAUTHORIZED;
        }
    }
}

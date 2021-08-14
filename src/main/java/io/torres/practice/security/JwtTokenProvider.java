package io.torres.practice.security;

import io.jsonwebtoken.*;
import io.torres.practice.exception.UnauthorizedException;
import io.torres.practice.exception.detail.UnauthorizedDetail;
import io.torres.practice.util.JsonUtils;
import io.torres.practice.vo.User;
import lombok.RequiredArgsConstructor;
import net.logstash.logback.encoder.org.apache.commons.lang3.StringUtils;
import org.eclipse.jetty.util.StringUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.token.Sha512DigestUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;

@RequiredArgsConstructor
@Component
public class JwtTokenProvider {

    private String secret;

    @Value("${spring.token.key}")
    private String key;

    @Value("${spring.token.prefix}")
    private String tokenPrefix;

    @Value("${spring.token.jwt.expire}")
    private long jwtExpireDuration;

    @Value("${spring.token.refresh.expire}")
    private long refreshExpireDuration;

    // 객체 초기화, secretKey를 인고딩
    @PostConstruct
    protected void init() {
        secret = Sha512DigestUtils.shaHex(key);
    }

    // 토큰 유효시간 30분
    private long tokenValidTime;

    private final UserDetailsService userDetailsService;

    // JWT 토큰 생성
    public String createToken(User user, String type) {
        Claims claims = Jwts.claims().setSubject(user.getSeq().toString()); // JWT payload 에 저장되는 정보단위
        claims.put("user", JsonUtils.objectToJson(user));
        Date now = new Date();

        if(type.equals("access")) tokenValidTime = jwtExpireDuration;
        else tokenValidTime = refreshExpireDuration;

        return Jwts.builder()
                .setClaims(claims) // 정보 저장
                .setIssuedAt(now) // 토큰 발행 시간 정보
                .setExpiration(new Date(now.getTime() + tokenValidTime)) // set Expire Time
                .signWith(SignatureAlgorithm.HS512, secret)  // 사용할 암호화 알고리즘과
                // signature 에 들어갈 secret값 세팅
                .compact();
    }

    // JWT 토큰에서 인증 정보 조회
    public Authentication getAuthentication(String token) {
        UserDetails userDetails = userDetailsService.loadUserByUsername(this.getUserPk(token));
        return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
    }

    // 토큰에서 회원 정보 추출
    public String getUserPk(String token) {
        return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody().getSubject();
    }

    // Request의 Header에서 token 값을 가져옵니다. "Authorization" : "TOKEN값'
    public String resolveToken(HttpServletRequest request) {
        return request.getHeader("Authorization");
    }

    // 토큰의 유효성 + 만료일자 확인
    public boolean validateToken(String jwtToken) {
        try {
            Jws<Claims> claims = Jwts.parser().setSigningKey(secret).parseClaimsJws(jwtToken);
            return !claims.getBody().getExpiration().before(new Date());
        } catch (Exception e) {
            return false;
        }
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

    // 토큰에서 회원 정보 추출
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

}

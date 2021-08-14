package io.torres.practice.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Claims;
import io.torres.practice.converter.UserConverter;
import io.torres.practice.entity.Employee;
import io.torres.practice.exception.EmptyDataException;
import io.torres.practice.repository.EmployeeRepository;
import io.torres.practice.service.RefreshTokenService;
import io.torres.practice.vo.User;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Set;

@Component
@RequiredArgsConstructor
public class TokenAuthenticationService {

	@Value("${spring.token.jwt.header}")
	private String jwtHeaderString;

	private final AuthorityProvider authorityProvider;
	private final JwtTokenProvider jwtTokenProvider;
	private ObjectMapper objectMapper = new ObjectMapper();

	private final UserConverter userConverter;

	@Autowired
	private EmployeeRepository employeeRepository;

	@Autowired
	private RefreshTokenService refreshTokenService;

	private final long empId = 327L;

	/**
	 * 토큰 생성
	 * @param response
	 * @param url
	 * @param isRedirect
	 * @throws IOException
	 */
	public void addAuthentication(HttpServletResponse response, String url, boolean isRedirect) throws IOException {

			try {
				Employee employee = employeeRepository.findById(empId).get();
				User user = userConverter.converts(employee);

				String accessToken = jwtTokenProvider.createToken(user, "Access");
				String refreshToken = jwtTokenProvider.createToken(user, "Refresh");
				refreshTokenService.save(user, refreshToken);

				if (isRedirect) {
					response.sendRedirect(url);
				} else {
					response.setHeader("accessToken", accessToken);
					response.setHeader("refreshToken", refreshToken);
				}
			}catch (EmptyDataException e) {
				if(isRedirect) {
					response.sendRedirect(url + "/notSync");
				}
				else {
					e.printStackTrace();
				}
			}

	}

	/**
	 * 토큰 생성
	 * @param response
	 * @param userId
	 */
	public void setTokenResponse(HttpServletResponse response, Long userId){
		Employee employeeEntity = employeeRepository.findByUserId(userId);
		User user = userConverter.converts(employeeEntity);

		String accessToken = jwtTokenProvider.createToken(user, "Access");
		String refreshToken = jwtTokenProvider.createToken(user, "Refresh");
		refreshTokenService.save(user, refreshToken);

		response.setHeader("accessToken",accessToken);
		response.setHeader("refreshToken",refreshToken);
	}

	/**
	 * jwt가 유효한 토큰인지 체크
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException
	 */
	public Authentication getAuthentication(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String token = jwtTokenProvider.getToken(request, jwtHeaderString);
		Claims claims = jwtTokenProvider.parseJwt(token);
		if (claims != null) {
			try {
				String userJson = claims.get("user").toString();
				User user = objectMapper.readValue(userJson, User.class);
				Set<GrantedAuthority> authoritySet = authorityProvider.getAuthoritySet(user);
				//권한 부여
				AuthenticationTokenImpl auth = new AuthenticationTokenImpl(Long.toString(user.getSeq()), authoritySet);
				auth.setAuthenticated(true);
				auth.setDetails(user);
				return auth;
			} catch (Exception e) {
				return new UsernamePasswordAuthenticationToken(null, null);
			}
		} else {
			return null;
		}
	}
}

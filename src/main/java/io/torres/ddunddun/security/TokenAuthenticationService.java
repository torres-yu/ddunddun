package io.torres.ddunddun.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Claims;
import io.torres.ddunddun.converter.UserConverter;
import io.torres.ddunddun.entity.Employee;
import io.torres.ddunddun.exception.EmptyDataException;
import io.torres.ddunddun.repository.EmployeeRepository;
import io.torres.ddunddun.service.RefreshTokenService;
import io.torres.ddunddun.util.CommonUtils;
import io.torres.ddunddun.vo.User;
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
	private final TokenProvider tokenProvider;
	private ObjectMapper objectMapper = new ObjectMapper();

	private final UserConverter userConverter;

	@Autowired
	private EmployeeRepository employeeRepository;

	@Autowired
	private RefreshTokenService refreshTokenService;

	private final long empId = 327L;


	public void addAuthentication(HttpServletRequest request, HttpServletResponse response, String url, boolean isRedirect) throws IOException {

			try {
				Employee employee = employeeRepository.findById(empId).get();
				User user = userConverter.converts(employee);

				String accessToken = tokenProvider.issueToken(user, "Access");
				String refreshToken = tokenProvider.issueToken(user, "Refresh");
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

	public void setTokenResponse(HttpServletResponse response, Long userId){
		Employee employeeEntity = employeeRepository.findByUserId(userId);
		User user = userConverter.converts(employeeEntity);

		String accessToken = tokenProvider.issueToken(user, "Access");
		String refreshToken = tokenProvider.issueToken(user, "Refresh");
		refreshTokenService.save(user, refreshToken);

		response.setHeader("accessToken",accessToken);
		response.setHeader("refreshToken",refreshToken);
	}

	public Authentication getAuthentication(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String token = tokenProvider.getToken(request, jwtHeaderString);
		Claims claims = tokenProvider.parseJwt(token);
		if (claims != null) {
			try {
				String userJson = claims.get("user").toString();
				User user = objectMapper.readValue(userJson, User.class);
				Set<GrantedAuthority> authoritySet = authorityProvider.getAuthoritySet(user);
				AuthenticationTokenImpl auth = new AuthenticationTokenImpl(Long.toString(user.getEmpId()), authoritySet);
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

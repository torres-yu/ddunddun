package io.torres.ddunddun.security.filter;

import com.google.gson.Gson;
import io.torres.ddunddun.exception.PlatformException;
import io.torres.ddunddun.result.LoResultResponse;
import io.torres.ddunddun.security.TokenAuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RequiredArgsConstructor
@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

	private final TokenAuthenticationService service;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {

			try {
				if (!request.getRequestURI().contains("/hello")) {
					Authentication authentication = service.getAuthentication(request);
					SecurityContextHolder.getContext().setAuthentication(authentication);
				}
				filterChain.doFilter(request, response);
			} catch (PlatformException e) {
				response.setHeader("Content-Type", "application/json;charset=UTF-8");
				response.setStatus(e.getHttpStatus().value());
				Gson gson = new Gson();
				response.getWriter().println(gson.toJson(new LoResultResponse<>(e.getHttpStatus().value(), e.getCode(), e.getMessage(), "")));
			}
	}
}

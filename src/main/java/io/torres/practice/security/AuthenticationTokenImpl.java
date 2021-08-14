package io.torres.practice.security;

import lombok.Setter;
import lombok.ToString;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.util.StringUtils;

import java.util.Collection;


@ToString(callSuper = true)
public class AuthenticationTokenImpl extends AbstractAuthenticationToken {

	@Setter
	private String empId;

	public AuthenticationTokenImpl(String principal, Collection<? extends GrantedAuthority> authorities) {
		super(authorities);
		this.empId = principal;
	}

	@Override
	public Object getCredentials() {
		return "";
	}

	@Override
	public Object getPrincipal() {
		return StringUtils.isEmpty(empId) ? "" : empId;
	}

}

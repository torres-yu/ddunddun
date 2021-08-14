package io.torres.practice.security;


import io.torres.practice.converter.UserConverter;
import io.torres.practice.repository.EmployeeRepository;
import io.torres.practice.vo.User;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
@RequiredArgsConstructor
public class AuthorityProvider {

	@Autowired
	private EmployeeRepository employeeRepository;

	private final UserConverter userConverter;

	public Set<GrantedAuthority> getAuthoritySet(String empNo) {
		User user = userConverter.converts(employeeRepository.findById(Long.parseLong(empNo)).get());
		return getAuthoritySet(user);
	}

	public Set<GrantedAuthority> getAuthoritySet(User user) {
		Set<GrantedAuthority> authoritySet = new HashSet<>();
		authoritySet.add(new SimpleGrantedAuthority("ROLE_USER"));
		//if(user.getAuthority() != null) authoritySet.add(new SimpleGrantedAuthority(user.getAuthority().name()));
		return authoritySet;
	}
}

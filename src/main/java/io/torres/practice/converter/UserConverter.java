package io.torres.practice.converter;

import io.torres.practice.entity.Employee;
import io.torres.practice.vo.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserConverter {
	User converts(Employee entity);
}

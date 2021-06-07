package io.torres.ddunddun.converter;

import io.torres.ddunddun.entity.Employee;
import io.torres.ddunddun.vo.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserConverter {
	User converts(Employee entity);
}

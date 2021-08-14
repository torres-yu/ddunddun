package io.torres.practice.security.redis;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface DefaultRedisRepository<T, ID> extends CrudRepository<T, ID> {
}

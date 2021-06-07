package io.torres.ddunddun.repository;

import io.torres.ddunddun.entity.RefreshToken;
import io.torres.ddunddun.security.redis.DefaultRedisRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RefreshTokenRepository extends DefaultRedisRepository<RefreshToken, Long> {
}

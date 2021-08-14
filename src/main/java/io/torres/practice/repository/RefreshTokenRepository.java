package io.torres.practice.repository;

import io.torres.practice.entity.RefreshToken;
import io.torres.practice.security.redis.DefaultRedisRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RefreshTokenRepository extends DefaultRedisRepository<RefreshToken, Long> {
}

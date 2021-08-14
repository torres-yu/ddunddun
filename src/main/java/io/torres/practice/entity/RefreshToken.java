package io.torres.practice.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

import javax.persistence.IdClass;

@Data
@RedisHash(value = "jwt", timeToLive = 86400) // 1 일 - 60 * 60 * 24
public class RefreshToken {
	@Id
	private Long id;

	private String refreshToken;
}

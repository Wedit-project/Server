package com.wedit.weditapp.global.auth.login.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RefreshTokenService {

    private final RedisTemplate<String, String> redisTemplate;

    @Value("${jwt.refresh.expiration}")
    private long refreshExpiration;

    // refreshToken -> email 매핑을 Redis에 저장
    public void saveRefreshToken(String refreshToken, String email) {
        redisTemplate.opsForValue()
                .set(refreshToken, email, refreshExpiration, java.util.concurrent.TimeUnit.MILLISECONDS);
    }

    // refreshToken으로 email 조회
    public String getEmailByRefreshToken(String refreshToken) {
        return redisTemplate.opsForValue().get(refreshToken);
    }

    // refreshToken 삭제 (추후 로그아웃 구현 시)
//    public void deleteRefreshToken(String refreshToken) {
//        redisTemplate.delete(refreshToken);
//    }
}

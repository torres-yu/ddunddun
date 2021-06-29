package io.torres.ddunddun.service;

import io.torres.ddunddun.entity.RefreshToken;
import io.torres.ddunddun.exception.EmptyDataException;
import io.torres.ddunddun.repository.RefreshTokenRepository;
import io.torres.ddunddun.vo.User;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class RefreshTokenService {

    @Autowired
    private RefreshTokenRepository refreshTokenRepository;

    /**
     * 리프레시 토큰 조회
     * @param user 대상자 정보
     * @return 리프레스 토큰
     * @exception EmptyDataException 리프레시 토큰 없음
     */
    @Transactional(readOnly = true)
    public String getRefreshToken(User user) {
        RefreshToken refreshToken = refreshTokenRepository.findById(user.getId())
                .orElseThrow(() -> new EmptyDataException("Not found RefreshToken"));

        return refreshToken.getRefreshToken();
    }

    /**
     * 리프레시 토큰 저장
     * @param user 대상자 정보
     * @param refreshToken 리프레시 토큰
     */
    @Transactional
    public void save(User user, String refreshToken) {
        RefreshToken refreshTokenEntity = new RefreshToken();
        refreshTokenEntity.setId(user.getId());
        refreshTokenEntity.setRefreshToken(refreshToken);

        refreshTokenRepository.save(refreshTokenEntity);
    }

    /**
     * 리프레시 토큰 삭제
     * @param user 대상자 정보
     */
    @Transactional
    public void remove(User user) {
        RefreshToken refreshToken = new RefreshToken();
        refreshToken.setId(user.getId());
        refreshTokenRepository.delete(refreshToken);
    }
}
